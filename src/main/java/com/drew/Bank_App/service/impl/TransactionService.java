package com.drew.Bank_App.service.impl;

import com.drew.Bank_App.dto.TransactionDto;
import com.drew.Bank_App.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transaction);

}
