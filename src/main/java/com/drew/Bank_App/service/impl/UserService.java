package com.drew.Bank_App.service.impl;


import com.drew.Bank_App.dto.BankResponse;
import com.drew.Bank_App.dto.UserRequest;

public interface UserService {


    BankResponse createAccount(UserRequest userRequest);

}
