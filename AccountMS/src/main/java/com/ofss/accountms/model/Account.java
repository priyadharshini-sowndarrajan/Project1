package com.ofss.accountms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNT_MS")
public class Account {

    @Id
    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "account_creation_date", nullable = false)
    private LocalDate accountCreationDate;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private double balance;

    @Column(name = "cust_id", nullable = false)
    private int customerId;

    @Column(name = "bank_id", nullable = false)
    private int bankId;

    public Account() {
        this.accountCreationDate = LocalDate.now();
    }

    public Account(String accountType, double balance, int customerId, int bankId) {
        this.accountCreationDate = LocalDate.now();
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
        this.bankId = bankId;
    }

    // Getters and Setters
    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public LocalDate getAccountCreationDate() { return accountCreationDate; }
    public void setAccountCreationDate(LocalDate accountCreationDate) { this.accountCreationDate = accountCreationDate; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getBankId() { return bankId; }
    public void setBankId(int bankId) { this.bankId = bankId; }
}
