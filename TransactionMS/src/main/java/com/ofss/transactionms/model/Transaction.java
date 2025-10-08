package com.ofss.transactionms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSACTION_MS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private int transactionId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private int customerId;

    @Column(name = "SOURCE_ACCOUNT_ID")
    private Integer sourceAccountId;

    @Column(name = "DESTINATION_ACCOUNT_ID")
    private Integer destinationAccountId;

    private LocalDate transactionDate;
    private String transactionType; // deposit, withdraw, transfer
    private double transactionAmount;

    public Transaction() {}

    public Transaction(int customerId, Integer sourceAccountId, Integer destinationAccountId,
                       LocalDate transactionDate, String transactionType, double transactionAmount) {
        this.customerId = customerId;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
    }

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Integer getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Integer sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public Integer getDestinationAccountId() {
		return destinationAccountId;
	}

	public void setDestinationAccountId(Integer destinationAccountId) {
		this.destinationAccountId = destinationAccountId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

    // Getters and setters
    // ...
}
