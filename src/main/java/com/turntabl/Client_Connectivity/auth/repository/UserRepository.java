package com.turntabl.Client_Connectivity.auth.repository;

//importing necessary libraries
import com.turntabl.Client_Connectivity.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


//class extends spring data jpa to store and retrieve data in a relational database(postgres)
public interface UserRepository extends JpaRepository<User, Long> {

    //defining findByEmail(String email) method which returns User object as available or not available.
    Optional<User> findByEmail(String email);
}
