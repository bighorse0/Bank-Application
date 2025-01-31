package com.drew.Bank_App.service.impl;

import com.drew.Bank_App.dto.EmailDetails;

public interface EmailService {
    void sendEmail(EmailDetails emailDetails);
    void sendEmailWithAttachment(EmailDetails emailDetails);
}
