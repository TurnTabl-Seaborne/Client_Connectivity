package com.turntabl.Client_Connectivity.auth.controller;

import com.turntabl.Client_Connectivity.auth.exception.UserNotFoundAdvice;
import com.turntabl.Client_Connectivity.auth.exception.UserNotFoundException;
import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @ResponseBody
    @PostMapping("/api/signup")
    ResponseEntity<String>  newUser(@RequestBody User newUser){
        Boolean exists = repository.findByEmail(newUser.getEmail()).map(userObj -> (userObj.getEmail().matches(newUser.getEmail())) ? true:false).orElse(false);
        return (exists) ? new ResponseEntity<>("user already exists", HttpStatus.FOUND): new ResponseEntity<>(repository.save(newUser).returnNameToString(), HttpStatus.CREATED);
    }

    @PostMapping("/api/signin")
    ResponseEntity<String> my_user(@RequestBody User user) {
        Boolean success_login = repository.findByEmail(user.getEmail()).map(userObj -> (userObj.getPassword().matches(user.getPassword())) ? true:false).orElse(false);
        return (success_login) ? new ResponseEntity<>("success", HttpStatus.ACCEPTED) : new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
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
