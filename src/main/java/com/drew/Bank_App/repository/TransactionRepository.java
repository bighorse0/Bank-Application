package com.drew.Bank_App.repository;

import com.drew.Bank_App.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
