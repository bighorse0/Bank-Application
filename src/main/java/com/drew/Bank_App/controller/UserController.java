package com.drew.Bank_App.controller;
import com.drew.Bank_App.dto.AccountInquiryRequest;
import com.drew.Bank_App.dto.BankResponse;
import com.drew.Bank_App.dto.CreditDebitRequest;
import com.drew.Bank_App.dto.UserRequest;
import com.drew.Bank_App.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    //either make it balanceInquiry or just balance
    @GetMapping("balance")
    public BankResponse balanceInquiry(@RequestBody AccountInquiryRequest accountInquiryRequest) {
        return userService.balanceInquiry(accountInquiryRequest);
    }

    //either make it nameInquiry or just name
    @GetMapping("name")
    public String nameInquiry(@RequestBody AccountInquiryRequest name) {
        return userService.nameInquiry(name);
    }

    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest creditRequest) {
        return userService.creditAccount(creditRequest);
    }

    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest debitRequest) {
        return userService.debitAccount(debitRequest);
    }
}
