package com.turntabl.Client_Connectivity.auth.repository;

//importing necessary libraries
import com.turntabl.Client_Connectivity.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


//class extends spring data jpa to store and retrieve data in a relational database(postgres)
public interface UserRepository extends JpaRepository<User, Long> {

    //defining findByEmail(String email) method which returns User object as available or not available.
    Optional<User> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.portfolio.user = :user where u.id = :user_id")
    void updatedNewUserPortfolio(@Param("user_id") Long user_id, @Param("user") User user);
}
