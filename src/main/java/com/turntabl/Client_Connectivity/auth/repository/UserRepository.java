package com.turntabl.Client_Connectivity.auth.repository;


import com.turntabl.Client_Connectivity.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
