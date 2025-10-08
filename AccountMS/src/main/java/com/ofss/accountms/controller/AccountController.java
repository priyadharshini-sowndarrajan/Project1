package com.ofss.accountms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofss.accountms.model.Account;
import com.ofss.accountms.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Get all accounts
    @GetMapping
    public ResponseEntity<Object> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // Get account details by ID (with Customer & Bank)
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getAccountDetails(@PathVariable int accountNumber) {
        return accountService.getAccountDetailsById(accountNumber);
    }

    // Add a new account
    @PostMapping("/add")
    public ResponseEntity<Object> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    // Delete an account
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<Object> deleteAccount(@PathVariable int accountNumber) {
        return accountService.deleteAccountById(accountNumber);
    }

    // Full update of an account
    @PutMapping("/update/{accountNumber}")
    public ResponseEntity<Object> updateAccount(@PathVariable int accountNumber, @RequestBody Account account) {
        return accountService.updateAccountById(accountNumber, account);
    }

    // Partial update of an account
    @PatchMapping("/update/{accountNumber}")
    public ResponseEntity<Object> updateAccountPartially(@PathVariable int accountNumber, @RequestBody Account account) {
        return accountService.updateAccountPartially(accountNumber, account);
    }
    // Deposit
    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@PathVariable int accountId, @RequestParam double amount) {
        return accountService.deposit(accountId, amount);
    }

    // Withdraw
    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@PathVariable int accountId, @RequestParam double amount) {
        return accountService.withdraw(accountId, amount);
    }

    // Transfer
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestParam int sourceId,
                                      @RequestParam int destId,
                                      @RequestParam double amount) {
        return accountService.transfer(sourceId, destId, amount);
    }
}
