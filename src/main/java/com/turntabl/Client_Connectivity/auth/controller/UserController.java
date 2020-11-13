package com.turntabl.Client_Connectivity.auth.controller;

import com.turntabl.Client_Connectivity.auth.exception.UserNotFoundAdvice;
import com.turntabl.Client_Connectivity.auth.exception.UserNotFoundException;
import com.turntabl.Client_Connectivity.auth.model.Response;
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
import org.springframework.web.server.ResponseStatusException;

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
    Response newUser(@RequestBody User newUser){
        Response clientRes = new Response();
        Boolean exists = repository.findByEmail(newUser.getEmail()).map(userObj -> (userObj.getEmail().matches(newUser.getEmail()))).orElse(false);
        if(!exists){
            clientRes.setData(repository.save(newUser).getName());
            clientRes.setStatus("created");
            clientRes.setCode(HttpStatus.CREATED.value());
            //return clientRes;
        }else{
            clientRes.setCode(HttpStatus.CONFLICT.value());
            clientRes.setStatus("user already exists");
           // return clientRes;
        }

        return clientRes;

       // return (exists) ? new ResponseEntity<>("user already exists", HttpStatus.FOUND): new ResponseEntity<>(repository.save(newUser).returnNameToString(), HttpStatus.CREATED);
    }

    @PostMapping("/api/signin")
    Response my_user(@RequestBody User user) {

        Response clientRes = new Response();


            Optional<User> optUser = repository.findByEmail(user.getEmail());

        if (optUser.isPresent()) {
            User user_db = optUser.get();
            if(user_db.getPassword().matches(user.getPassword())){
                clientRes.setStatus("success");
                clientRes.setCode(HttpStatus.ACCEPTED.value());
                clientRes.setData(user_db.getName());
            }else{
                clientRes.setStatus("invalid credentials");
                clientRes.setCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        else {
            clientRes.setStatus("user not found");
            clientRes.setCode(HttpStatus.NOT_FOUND.value());
        }


            return clientRes;



        //Boolean success_login = repository.findByEmail(user.getEmail()).map(userObj -> (userObj.getPassword().matches(user.getPassword())) ? true:false).orElse(false);
        //return (success_login) ? new ResponseEntity<>("success", HttpStatus.ACCEPTED) : new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
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
