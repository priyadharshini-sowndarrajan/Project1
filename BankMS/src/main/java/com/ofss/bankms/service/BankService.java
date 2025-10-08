package com.ofss.bankms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofss.bankms.model.Bank;
import com.ofss.bankms.repository.BankRepository;



@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    // List all banks
    public List<Bank> listBanks() {
        return (List<Bank>) bankRepository.findAll();
    }

    // Get a bank by ID
    public ResponseEntity<Object> getABankById(int bankId) {
        Optional<Bank> bank = bankRepository.findById(bankId);
        if (bank.isPresent()) {
            return ResponseEntity.ok(bank.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Bank with id " + bankId + " not found");
    }

    // Add a new bank
    public ResponseEntity<Object> addBank(Bank newBank) {
        bankRepository.save(newBank);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bank added successfully");
    }

    // Delete bank by ID
    public ResponseEntity<Object> deleteBankById(int bankId) {
        Optional<Bank> optional = bankRepository.findById(bankId);
        if (optional.isPresent()) {
            bankRepository.deleteById(bankId);
            return ResponseEntity.ok("Bank deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Bank with id " + bankId + " not found");
    }

    // Update bank completely
    public ResponseEntity<Object> updateBankById(int bankId, Bank bank) {
        Optional<Bank> optional = bankRepository.findById(bankId);
        if (optional.isPresent()) {
            Bank existingBank = optional.get();
            existingBank.setBankName(bank.getBankName());
            existingBank.setBranchName(bank.getBranchName());
            existingBank.setIfscCode(bank.getIfscCode());
            bankRepository.save(existingBank);
            return ResponseEntity.ok("The bank has been updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The given bank id is not present");
    }

    // Update bank partially
    public ResponseEntity<Object> updateBankByIdPartially(int bankId, Bank bank) {
        Optional<Bank> optional = bankRepository.findById(bankId);
        if (optional.isPresent()) {
            Bank existingBank = optional.get();
            if (bank.getBankName() != null) existingBank.setBankName(bank.getBankName());
            if (bank.getBranchName() != null) existingBank.setBranchName(bank.getBranchName());
            if (bank.getIfscCode() != null) existingBank.setIfscCode(bank.getIfscCode());
            bankRepository.save(existingBank);
            return ResponseEntity.ok("The bank has been updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The given bank id is not present");
    }
}
