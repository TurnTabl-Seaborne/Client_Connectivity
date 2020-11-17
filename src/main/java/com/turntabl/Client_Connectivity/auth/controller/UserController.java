package com.turntabl.Client_Connectivity.auth.controller;


//importing necessary libraries
import com.turntabl.Client_Connectivity.auth.exception.UserNotFoundException;
import com.turntabl.Client_Connectivity.auth.model.Response;
import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;






//RestController annotation applies to mark UserController class as a request handler.
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController implements UserDetailsService {


    //declare variable of type userRepository and injecting it implicitly with Autowired annotation.
    @Autowired
    private final UserRepository repository;




   //constructor for UserController class.
    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }





   //get all users endpoint
    @GetMapping("/api/users")
    List<User> allUsers(){
        return repository.findAll();
    }





    //sign up endpoint
    @PostMapping("/api/signup")
    //function that takes user object and returns response of type Response.
    Response newUser(@RequestBody User newUser){

        //declare variable of type Response to hold response.
        Response clientRes = new Response();


        //check if user is already registered on the platform.
        Boolean exists = repository.findByEmail(newUser.getEmail()).map(userObj -> (userObj.getEmail().matches(newUser.getEmail()))).orElse(false);


        if(!exists){

            //if user doesn't exit
            //save user details and set name on Response Object.
            clientRes.setData(repository.save(newUser).getName());

            //set status of Response Object.
            clientRes.setStatus("created");

            //set http code on Response Object.
            clientRes.setCode(HttpStatus.CREATED.value());

        }else{

            //if user exists
            //set http code on Response Object.
            clientRes.setCode(HttpStatus.CONFLICT.value());

            //set status on Response Object.
            clientRes.setStatus("user already exists");

        }


        //finally, return Response
        return clientRes;
    }







    //sign in endpoint
    @PostMapping("/api/signin")
    //function that takes User and returns response of type Response.
    Response my_user(@RequestBody User user) {



        //declare variable of type Response to hold response.
        Response clientRes = new Response();



        //check if user is registered on the platform. Returns User as available or not available.
        Optional<User> optUser = repository.findByEmail(user.getEmail());




        if (optUser.isPresent()) {

            //get user from Optional User variable.
            User user_db = optUser.get();


            //check if password provided matches the one in the database.
            if(user_db.getPassword().matches(user.getPassword())){

                //if password matches
                //set status on Response Object.
                clientRes.setStatus("success");

                //set http response code on Response Object.
                clientRes.setCode(HttpStatus.ACCEPTED.value());

                //set data on Response Object.
                clientRes.setData(user_db.getName());


            }else{

                //if password doesn't match.
                //set status on Response Object.
                clientRes.setStatus("invalid credentials");


                //set http code on Response Object.
                clientRes.setCode(HttpStatus.UNAUTHORIZED.value());


            }
        }
        else {

            //if user doesn't exist in database
            //set status on Response Object.
            clientRes.setStatus("user not found");

            //set http status code on Response Object.
            clientRes.setCode(HttpStatus.NOT_FOUND.value());


        }


        //finally, return Response
            return clientRes;


    }




    //endpoint for getting user by ID.
    @GetMapping("/api/users/{id}")
    //function that takes user ID of type long and returns User
    User oneUser(@PathVariable Long id){

        //return user if found else throw user nopt found exception.
        return  repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));


    }



    //endpoint for updating user details
    @PutMapping("/api/users/{id}")
    //function that takes User and ID and returns user details.
    User updateUser(@RequestBody User newUser, @PathVariable Long id){

        //find user by id
        //if found, replace the user details with the one provided and return User.
        return repository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return repository.save(user);
        }).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
    }



    //delete user endpoint.
    @DeleteMapping("/api/users/{id}")
    //function that takes ID of type long and deletes user.
    void deleteUSer(@PathVariable Long id){
        repository.deleteById(id);
    }



    //overriding loadByUsername method from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //find user by email provided and save response in Optional<User> variable.
        final Optional<User> optionalUser = repository.findByEmail(email);


        if (optionalUser.isPresent()) {

            //if user is available in Optional type variable, get the User and return the user details.
            return (UserDetails) optionalUser.get();
        }
        else {

            //if user is not found, throw username not found exception.
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }
}
