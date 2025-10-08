package com.ofss.accountms.model;

public class AccountDTO {

    private Account account;
    private Object customer; // fetched from Customer MS
    private Object bank;     // fetched from Bank MS

    public AccountDTO() {}

    public AccountDTO(Account account, Object customer, Object bank) {
        this.account = account;
        this.customer = customer;
        this.bank = bank;
    }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Object getCustomer() { return customer; }
    public void setCustomer(Object customer) { this.customer = customer; }

    public Object getBank() { return bank; }
    public void setBank(Object bank) { this.bank = bank; }
}
