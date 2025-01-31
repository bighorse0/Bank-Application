package com.drew.Bank_App.controller;


import com.drew.Bank_App.entity.Transaction;
import com.drew.Bank_App.service.impl.BankStatement;
import com.drew.Bank_App.service.impl.TransactionService;
import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bankStatement")
public class TransactionController {

    private BankStatement bankStatement;

    // should I rename method to createStatement?
    @GetMapping
    public List<Transaction> getStatement(@RequestParam String accountNumber,
                                          @RequestParam String fromDate,
                                          @RequestParam String toDate) throws DocumentException, FileNotFoundException {

        return bankStatement.createStatement(accountNumber, fromDate, toDate);
    }

}
