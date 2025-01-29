package com.drew.Bank_App.utils;

import java.time.Year;
import java.util.Random;

public class AccountUtils {
    public static final String ACC_CREATED = "0";
    public static final String ACC_CREATED_MESSAGE = "Account created";
    public static final String ACC_EXISTS = "1";
    public static final String ACC_EXISTS_MESSAGE = "An account with this email already exists";
    public static final String ACC_NOT_EXISTS = "2";
    public static final String ACC_NOT_EXISTS_MESSAGE = "An account with this email does not exist";
    public static final String ACC_HAS_BEEN_CREDITED = "3";
    public static final String ACC_HAS_BEEN_CREDITED_MESSAGE = "Account has been credited successfully";
    public static final String ACC_HAS_INSUFFICIENT_FUNDS = "4";
    public static final String ACC_HAS_INSUFFICIENT_FUNDS_MESSAGE = "Account has been insufficient funds";
    public static final String ACC_HAS_BEEN_DEBITED = "5";
    public static final String ACC_HAS_BEEN_DEBITED_MESSAGE = "Account has been debited";

    public static String generateAccountNumber() {
        /**
         * Six-digit account number
         */
        Random random = new Random();
        int accountNumber = 100000 + random.nextInt(900000);
        return String.valueOf(accountNumber);
    }

}
