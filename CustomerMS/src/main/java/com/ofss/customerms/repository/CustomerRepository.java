package com.ofss.customerms.repository;

import org.springframework.data.repository.CrudRepository;
import com.ofss.customerms.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
