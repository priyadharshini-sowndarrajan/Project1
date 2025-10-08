package com.ofss.bankms.repository;

import org.springframework.data.repository.CrudRepository;

import com.ofss.bankms.model.Bank;

public interface BankRepository extends CrudRepository<Bank, Integer> {
    // You can add custom queries here if needed
}

