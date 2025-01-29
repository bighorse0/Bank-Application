package com.drew.Bank_App.service.impl;
import com.drew.Bank_App.dto.*;
import com.drew.Bank_App.entity.User;
import com.drew.Bank_App.repository.UserRepository;
import com.drew.Bank_App.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        /**
         * Check if user already exists in db by email
         * If not,
         * Create a account. Save user into database.
         */
        // TODO: Email isn't the best way to do this. Maybe someone can have multiple accounts tied to an email, etc.
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACC_EXISTS)
                    .responseMessage(AccountUtils.ACC_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .build();

        User savedUser = userRepository.save(newUser);

        EmailDetails emailDetails = EmailDetails.builder()
                .receiver(savedUser.getEmail())
                .subject("Bank Account Creation")
                .messageBody("An account for " + savedUser.getFirstName() + " has been created. Your account number is " + savedUser.getAccountNumber())
                .build();
        emailService.sendEmail(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACC_CREATED)
                .responseMessage(AccountUtils.ACC_CREATED_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .build())
                .build();
    }

    @Override
    public BankResponse balanceInquiry(AccountInquiryRequest request) {
        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!accountExists) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACC_NOT_EXISTS)
                    .responseMessage(AccountUtils.ACC_NOT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

         User savedUser = userRepository.findByAccountNumber(request.getAccountNumber());
         return BankResponse.builder()
                 .responseCode(AccountUtils.ACC_EXISTS)
                 .responseMessage(AccountUtils.ACC_EXISTS_MESSAGE)
                 .accountInfo(AccountInfo.builder()
                         .accountBalance(savedUser.getAccountBalance())
                         .accountNumber(savedUser.getAccountNumber())
                         .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                         .build())
                 .build();
    }

    @Override
    public String nameInquiry(AccountInquiryRequest request) {
        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!accountExists) {
            return AccountUtils.ACC_NOT_EXISTS_MESSAGE;
        }
        User savedUser = userRepository.findByAccountNumber(request.getAccountNumber());
        return savedUser.getFirstName() + savedUser.getLastName();
    }

    //add money to account
    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {
        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!accountExists) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACC_NOT_EXISTS)
                    .responseMessage(AccountUtils.ACC_NOT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User user = userRepository.findByAccountNumber(request.getAccountNumber());
        user.setAccountBalance(user.getAccountBalance().add(request.getAmount()));
        userRepository.save(user);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACC_HAS_BEEN_CREDITED)
                .responseMessage(AccountUtils.ACC_HAS_BEEN_CREDITED_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(user.getFirstName() + " " + user.getLastName())
                        .accountNumber(user.getAccountNumber())
                        .accountBalance(user.getAccountBalance())
                        .build())
                .build();

    }

    //subtract money from account
    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {
        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!accountExists) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACC_NOT_EXISTS)
                    .responseMessage(AccountUtils.ACC_NOT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User user = userRepository.findByAccountNumber(request.getAccountNumber());
        if (user.getAccountBalance().compareTo(request.getAmount()) < 0) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACC_HAS_INSUFFICIENT_FUNDS)
                    .responseMessage(AccountUtils.ACC_HAS_INSUFFICIENT_FUNDS_MESSAGE)
                    .accountInfo(null)
                    .build();
        } else {
            user.setAccountBalance(user.getAccountBalance().subtract(request.getAmount()));
            userRepository.save(user);

            return BankResponse.builder()
                    .responseCode(AccountUtils.ACC_HAS_BEEN_DEBITED)
                    .responseMessage(AccountUtils.ACC_HAS_BEEN_DEBITED_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(user.getAccountNumber())
                            .accountName(user.getFirstName() + " " + user.getLastName())
                            .accountBalance(user.getAccountBalance())
                            .build())
                    .build();
        }
    }
}