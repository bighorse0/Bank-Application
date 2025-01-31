package com.drew.Bank_App.service.impl;

import com.drew.Bank_App.dto.EmailDetails;
import com.drew.Bank_App.entity.Transaction;
import com.drew.Bank_App.entity.User;
import com.drew.Bank_App.repository.TransactionRepository;
import com.drew.Bank_App.repository.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * retrieve list of transactions associated with an account for a given time range
 * generate pdfs for it
 * send pdf to email
 */
@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

    private UserRepository userRepository;

    private TransactionRepository transactionRepository;

    private EmailService emailService;

    // where you would like the Pdf saved
    private static final String FILE = "bank_statement.pdf";

    public List<Transaction> createStatement(String accountNumber, String fromDate, String toDate) throws DocumentException, FileNotFoundException {
        LocalDate start = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE);

        // Filters transactions after start day and before end day
        // I guess in java can only use map, filter, reduce on streams?
        List<Transaction> transactionsList = transactionRepository.findAll().stream().filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
                .filter(transaction -> transaction.getCreatedAt().isAfter(start.atStartOfDay())).filter(transaction -> transaction.getCreatedAt().isBefore(end.atStartOfDay())).toList();
        //check isEquals vs isAfter

        User user = userRepository.findByAccountNumber(accountNumber);
        String accountName = user.getFirstName() + " " + user.getLastName();

        // creating the pdf of account statement / summary

        // !! I believe this is iText 7 so the way you use the Rectangle and Document constructor is different than older versions
        // TODO: might need to adjust the rectangle size depending on output
        // make into helper function
        com.itextpdf.text.Rectangle statementSize = new Rectangle(100, 100);
        Document doc = new Document(statementSize);
        OutputStream outputStream = new FileOutputStream(FILE);
        PdfWriter.getInstance(doc, outputStream);
        doc.open();

        PdfPTable bankInfoTable = new PdfPTable(1);
        PdfPCell bankName = new PdfPCell(new Phrase("THE BANK!"));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.GREEN);
        bankName.setPadding(20f);

        PdfPCell bankAddress = new PdfPCell(new Phrase("100 Bank Address, United States"));
        bankAddress.setBorder(0);
        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAddress);

        PdfPTable statementInfo = new PdfPTable(2);
        PdfPCell customerInfo = new PdfPCell(new Phrase("Start date" + fromDate));
        customerInfo.setBorder(0);
        PdfPCell statement = new PdfPCell(new Phrase("ACCOUNT STATEMENT"));
        statement.setBorder(0);
        PdfPCell endDate = new PdfPCell(new Phrase("End date" + toDate));
        endDate.setBorder(0);

        PdfPCell name = new PdfPCell(new Phrase("Account Name: " + accountName));
        name.setBorder(0);
        PdfPCell space = new PdfPCell();
        space.setBorder(0);
        PdfPCell address = new PdfPCell(new Phrase("Address: " + user.getAddress()));
        address.setBorder(0);

        PdfPTable transactionTable = new PdfPTable(4);
        PdfPCell date = new PdfPCell(new Phrase("DATE"));
        date.setBorder(0);
        date.setBackgroundColor(BaseColor.GREEN);
        PdfPCell transactionType = new PdfPCell(new Phrase("TRANSACTION TYPE"));
        transactionType.setBackgroundColor(BaseColor.GREEN);
        transactionType.setBorder(0);
        PdfPCell amount = new PdfPCell(new Phrase("AMOUNT"));
        amount.setBackgroundColor(BaseColor.GREEN);
        amount.setBorder(0);
        PdfPCell status = new PdfPCell(new Phrase("STATUS"));
        status.setBackgroundColor(BaseColor.GREEN);
        status.setBorder(0);

        transactionTable.addCell(date);
        transactionTable.addCell(transactionType);
        transactionTable.addCell(amount);
        transactionTable.addCell(status);

        transactionsList.forEach(transaction -> {
            transactionTable.addCell(transaction.getCreatedAt().toString());
            transactionTable.addCell(transaction.getTransactionType());
            transactionTable.addCell(transaction.getAmount().toString());
            transactionTable.addCell(transaction.getStatus());
        });

        statementInfo.addCell(customerInfo);
        statementInfo.addCell(statement);
        statementInfo.addCell(endDate);
        statementInfo.addCell(name);
        statementInfo.addCell(space);
        statementInfo.addCell(address);

        doc.add(bankInfoTable);
        doc.add(statementInfo);
        doc.add(transactionTable);

        doc.close();

        // send newly created pdf of account statement to users email

        EmailDetails emailDetails = EmailDetails.builder()
                .receiver(user.getEmail())
                .subject("Bank Statement of " + accountName)
                .messageBody("Your account statement is attached. ")
                .attachment(FILE)
                .build();

        emailService.sendEmailWithAttachment(emailDetails);

        return transactionsList;
    }
}
