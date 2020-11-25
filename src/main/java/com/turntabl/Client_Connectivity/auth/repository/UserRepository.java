package com.turntabl.Client_Connectivity.auth.repository;

//importing necessary libraries
import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//class extends spring data jpa to store and retrieve data in a relational database(postgres)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //defining findByEmail(String email) method which returns User object as available or not available.
    Optional<User> findByEmail(String email);

//    @Query("select p from Portfolio p where p.user.UserId = ?1")
//    List<Portfolio> findAllByUserId(Long user_id);
    @Query("select u from User u ")
    List<User> findAllUser();

}
