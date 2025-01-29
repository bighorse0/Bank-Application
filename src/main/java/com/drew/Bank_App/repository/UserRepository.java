package com.drew.Bank_App.repository;

import com.drew.Bank_App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//provides methods to talk to database
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Boolean existsByAccountNumber(String accountNumber);
    User findByAccountNumber(String accountNumber);
}
