package com.ofss.transactionms.repository;

import org.springframework.data.repository.CrudRepository;
import com.ofss.transactionms.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
