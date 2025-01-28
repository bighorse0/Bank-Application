package com.drew.Bank_App.controller;
import com.drew.Bank_App.dto.BankResponse;
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
    public BankResponse createdAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }
}
