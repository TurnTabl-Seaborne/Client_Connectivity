package com.turntabl.Client_Connectivity.auth.controller;


//importing necessary libraries
import com.turntabl.Client_Connectivity.auth.model.*;
import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import com.turntabl.Client_Connectivity.portfolio.doa.PortfolioDao;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//RestController annotation applies to mark UserController class as a request handler.
@RestController
public class UserController implements UserDetailsService {


    //declare variable of type userRepository and injecting it implicitly with Autowired annotation.
    @Autowired
    private final UserRepository repository;


    @Autowired
    private final PortfolioDao portfolioDao;



    @Autowired
    private PasswordEncoder passwordEncoder;




   //constructor for UserController class.
    public UserController(UserRepository userRepository, PortfolioDao portfolioDao) {
        this.repository = userRepository;
        this.portfolioDao = portfolioDao;
    }





   //get all users endpoint
    @GetMapping("/api/users")
    List<UserResponse> allUsers(){
        return repository.findAll().stream().map(
                user -> {
                    UserResponse userListResponse = new UserResponse();
                    userListResponse.setUser_id(user.getUserId());
                    userListResponse.setName(user.getName());
                    userListResponse.setEmail(user.getEmail());
                    userListResponse.setRole(user.getRole().toString());
                    userListResponse.setPortfolio_id(user.getPortfolio().stream().map(
                            portfolio -> {
                                return portfolio.getPortfolioId();
                            }
                    ).collect(Collectors.toList()));

                    return userListResponse;
                }
        ).collect(Collectors.toList());
    }





    //sign up endpoint
    @PostMapping("/api/signup")
    //function that takes user object and returns response of type Response.
    UserDataResponse newUser(@RequestBody User newUser){

        UserData userData = new UserData();
        UserDataResponse clientRes = new UserDataResponse();


        User userToSave = new User();



        //check if user is already registered on the platform.
        Boolean exists = repository.findByEmail(newUser.getEmail()).map(userObj -> (userObj.getEmail().matches(newUser.getEmail()))).orElse(false);


        if(!exists){

            User user = new User();
            user.setName(newUser.getName());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setEmail(newUser.getEmail());
            user.setRole(Role.CLIENT);
            user = repository.save(user);


            Portfolio portfolio = new Portfolio();
            portfolio.assisgnToUser(user);
            Portfolio newPortfolio = portfolioDao.save(portfolio);


            userData.setEmail(user.getEmail());
            userData.setName(user.getName());
            userData.setUserId(user.getUserId());
            userData.setRole(user.getRole().toString());

            //if user doesn't exit
            //save user details and set name on Response Object.
            clientRes.setData(userData);

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
    @PostMapping("/api/admin")
    //function that takes User and returns response of type Response.
    UserDataResponse adminSignIn(@RequestBody User user) {

        UserDataResponse userDataResponse = new UserDataResponse();
        UserData userData = new UserData();

        User admin = repository.findByEmail(user.getEmail()).get();



        if(passwordEncoder.matches(user.getPassword(), admin.getPassword())){
            userData.setUserId(admin.getUserId());
            userData.setEmail(admin.getEmail());
            userData.setName(admin.getName());

            userDataResponse.setCode(HttpStatus.OK.value());
            userDataResponse.setData(userData);
            userDataResponse.setStatus("success");

        }else{
            userDataResponse.setCode(HttpStatus.FORBIDDEN.value());
            userDataResponse.setStatus("invalid email/password");
        }

        return userDataResponse;

    }






    //sign in endpoint
    @PostMapping("/api/signin")
    //function that takes User and returns response of type Response.
    UserDataResponse my_user(@RequestBody User user) {



        //declare variable of type Response to hold response.
        UserDataResponse clientRes = new UserDataResponse();



        //check if user is registered on the platform. Returns User as available or not available.
        Optional<User> optUser = repository.findByEmail(user.getEmail());




        if (optUser.isPresent()) {

            UserData userData = new UserData();

            //get user from Optional User variable.
            User user_db = optUser.get();


            //check if password provided matches the one in the database.
            if(passwordEncoder.matches(user.getPassword(),user_db.getPassword())){

                userData.setName(user_db.getName());
                userData.setEmail(user_db.getEmail());
                userData.setUserId(user_db.getUserId());
                userData.setRole(user_db.getRole().toString());

                //if password matches
                //set status on Response Object.
                clientRes.setStatus("success");

                //set http response code on Response Object.
                clientRes.setCode(HttpStatus.ACCEPTED.value());

                //set data on Response Object.
                clientRes.setData(userData);


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
    UserResponse oneUser(@PathVariable Long id){
        User user = repository.findById(id).get();

        UserResponse response =  new UserResponse();
        response.setUser_id(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPortfolio_id(user.getPortfolio().stream().map(
                portfolio -> {
                    return portfolio.getPortfolioId();
                }
        ).collect(Collectors.toList()));

        return response;


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
