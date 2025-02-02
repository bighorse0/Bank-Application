package com.drew.Bank_App.controller;
import com.drew.Bank_App.dto.*;
import com.drew.Bank_App.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Account APIs")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(
            summary = "Create New User Account",
            description = "Create a new user & gives that user an account ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201"
    )
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @Operation(
            summary = "Get account balance of a user",
            description = "Return the account balance a user has currently"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping("balance")
    public BankResponse balanceInquiry(@RequestBody AccountInquiryRequest accountInquiryRequest) {
        return userService.balanceInquiry(accountInquiryRequest);
    }

    @Operation(
            summary = "Get account name of a user",
            description = "Return the account name of a user given accountID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping("name")
    public String nameInquiry(@RequestBody AccountInquiryRequest name) {
        return userService.nameInquiry(name);
    }

    @Operation(
            summary = "Add funds to a users account",
            description = "Credits the requested account with specific amount of funds"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201"
    )
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest creditRequest) {
        return userService.creditAccount(creditRequest);
    }

    @Operation(
            summary = "Delete funds from a users account",
            description = "Subtracts the requested amount from the specified account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201"
    )
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest debitRequest) {
        return userService.debitAccount(debitRequest);
    }

    @Operation(
            summary = "Transfers funds from a sender to a receiver",
            description = "The sender transfers money to receiver. Sender will have that amount of funds taken from account, receiver will have those funds credited to their account."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201"
    )
    @PostMapping("transfer")
    public BankResponse transferFunds(@RequestBody TransferRequest transferRequest) {
        return userService.transfer(transferRequest);
    }
}
