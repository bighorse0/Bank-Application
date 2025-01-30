package com.drew.Bank_App.service.impl;


import com.drew.Bank_App.dto.*;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceInquiry(AccountInquiryRequest request);
    String nameInquiry(AccountInquiryRequest request);
    BankResponse creditAccount(CreditDebitRequest request);
    BankResponse debitAccount(CreditDebitRequest request);
    BankResponse transfer(TransferRequest request);
}
