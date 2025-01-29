package com.drew.Bank_App.utils;

import java.time.Year;
import java.util.Random;

public class AccountUtils {
    public static final String ACC_CREATED = "0";
    public static final String ACC_CREATED_MESSAGE = "Account created";
    public static final String ACC_EXISTS = "1";
    public static final String ACC_EXISTS_MESSAGE = "An account with this email already exists";

    public static String generateAccountNumber() {
        /**
         * Six-digit account number
         */
        Random random = new Random();
        int accountNumber = 100000 + random.nextInt(900000);
        return String.valueOf(accountNumber);
    }

}
