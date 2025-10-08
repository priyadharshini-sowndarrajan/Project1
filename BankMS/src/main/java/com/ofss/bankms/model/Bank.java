package com.ofss.bankms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BANK_MS")
public class Bank {

    @Id
    @Column(name = "BANK_ID")
    private int bankId;

    @Column(name = "BANK_NAME", nullable = false)
    private String bankName;

    @Column(name = "BRANCH_NAME")
    private String branchName;

    @Column(name = "IFSC_CODE", unique = true, nullable = false)
    private String ifscCode;

    public Bank() {}

    public Bank(int bankId, String bankName, String branchName, String ifscCode) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
    }

    // Getters and Setters
    public int getBankId() { return bankId; }
    public void setBankId(int bankId) { this.bankId = bankId; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }
}