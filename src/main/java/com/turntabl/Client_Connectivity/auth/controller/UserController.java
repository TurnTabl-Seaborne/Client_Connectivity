package com.turntabl.Client_Connectivity.auth.controller;

import com.turntabl.Client_Connectivity.auth.exception.UserNotFoundException;
import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }


    @GetMapping("/api/users")
    List<User> allUsers(){
        return repository.findAll();
    }

    @PostMapping("/api/signup")
    User newUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @PostMapping("/api/signin")
    String my_user(@RequestBody User user) {
       // User my_user = (User) loadUserByUsername(user.getEmail());
        return "user";
    }

    @GetMapping("/api/users/{id}")
    User oneUser(@PathVariable Long id){
        return  repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/api/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return repository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return repository.save(user);
        }).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
    }


    @DeleteMapping("/api/users/{id}")
    void deleteUSer(@PathVariable Long id){
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> optionalUser = repository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return (UserDetails) optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }
}
