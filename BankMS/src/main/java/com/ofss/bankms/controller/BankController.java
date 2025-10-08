package com.ofss.bankms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofss.bankms.model.Bank;
import com.ofss.bankms.service.BankService;



@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    // GET /banks -> list all banks
    @GetMapping
    public List<Bank> listAllBanks() {
        return bankService.listBanks();
    }

    // GET /banks/{id} -> get bank by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getABankById(@PathVariable("id") int bankId) {
        return bankService.getABankById(bankId);
    }

    // POST /banks -> add new bank
    @PostMapping("/add")
    public ResponseEntity<Object> addABank(@RequestBody Bank bank) {
        return bankService.addBank(bank);
    }

    // DELETE /banks/{id} -> delete bank by ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteBankById(@PathVariable("id") int bankId) {
        return bankService.deleteBankById(bankId);
    }

    // PUT /banks/{id} -> update bank completely
    @PutMapping("/id/{id}")
    public ResponseEntity<Object> updateBank(@PathVariable("id") int bankId, @RequestBody Bank bank) {
        return bankService.updateBankById(bankId, bank);
    }

    // PATCH /banks/{id} -> update bank partially
    @PatchMapping("/id/{id}")
    public ResponseEntity<Object> updateBankPartially(@PathVariable("id") int bankId, @RequestBody Bank bank) {
        return bankService.updateBankByIdPartially(bankId, bank);
    }
}