package com.drew.Bank_App.service.impl;


import com.drew.Bank_App.dto.AccountInquiryRequest;
import com.drew.Bank_App.dto.BankResponse;
import com.drew.Bank_App.dto.CreditDebitRequest;
import com.drew.Bank_App.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceInquiry(AccountInquiryRequest request);
    String nameInquiry(AccountInquiryRequest request);
    BankResponse creditAccount(CreditDebitRequest request);
    BankResponse debitAccount(CreditDebitRequest request);
}
