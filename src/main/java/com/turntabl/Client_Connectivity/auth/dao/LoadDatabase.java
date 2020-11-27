//package com.turntabl.Client_Connectivity.auth.dao;
//
//import com.turntabl.Client_Connectivity.auth.model.Role;
//import com.turntabl.Client_Connectivity.auth.model.User;
//import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//
//@Component
//public class LoadDatabase implements ApplicationRunner {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public LoadDatabase(UserRepository repository){
//        this.userRepository = repository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//
//
//        User admin = new User();
//        admin.setEmail("admin@gmail.com");
//        admin.setName("Dorothy Efua Ewuah");
//        admin.setRole(Role.ADMIN);
//        admin.setPassword(passwordEncoder.encode("admin"));
//
//        Optional<User> admin1 = userRepository.findByEmail("admin@gmail.com");
//
//        if(!admin1.isPresent()){
//                userRepository.save(admin);
//                System.out.println("admin created");
//        }
//
//
//
//
//    }
//}
