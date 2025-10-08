package com.ofss.transactionms.controller;

import com.ofss.transactionms.model.Transaction;
import com.ofss.transactionms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit/accounts/{accountId}")
    public ResponseEntity<?> deposit(@PathVariable int accountId, @RequestBody Map<String, Double> payload) {
        try {
            double amount = payload.get("depositAmount");
            Transaction t = transactionService.deposit(accountId, amount);
            return ResponseEntity.ok(t);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/withdraw/accounts/{accountId}")
    public ResponseEntity<?> withdraw(@PathVariable int accountId, @RequestBody Map<String, Double> payload) {
        try {
            double amount = payload.get("withdrawAmount");
            Transaction t = transactionService.withdraw(accountId, amount);
            return ResponseEntity.ok(t);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transfer/from/{sourceId}/to/{destId}")
    public ResponseEntity<?> transfer(@PathVariable int sourceId, @PathVariable int destId, @RequestBody Map<String, Double> payload) {
        try {
            double amount = payload.get("transferAmount");
            Transaction t = transactionService.transfer(sourceId, destId, amount);
            return ResponseEntity.ok(t);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
