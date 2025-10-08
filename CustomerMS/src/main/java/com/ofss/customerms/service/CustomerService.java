package com.ofss.customerms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofss.customerms.model.Customer;
import com.ofss.customerms.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listCustomers() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(allCustomers::add);
        return allCustomers;
    }

    public ResponseEntity<Object> getACustomerById(int custId) {
        Optional<Customer> customer = customerRepository.findById(custId);
        return customer.isPresent() 
                ? ResponseEntity.ok(customer.get()) 
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Customer ID not found");
    }

    public ResponseEntity<Object> addCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Customer added successfully");
    }

    public ResponseEntity<Object> deleteACustomerById(int custId) {
        if(customerRepository.existsById(custId)) {
            customerRepository.deleteById(custId);
            return ResponseEntity.ok("Customer deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Customer ID not found");
    }

    public ResponseEntity<Object> updateACustomerById(int custId, Customer cust) {
        Optional<Customer> optional = customerRepository.findById(custId);
        if(optional.isPresent()) {
            Customer existing = optional.get();
            existing.setFirstName(cust.getFirstName());
            existing.setLastName(cust.getLastName());
            existing.setPhoneNumber(cust.getPhoneNumber());
            existing.setEmailId(cust.getEmailId());
            customerRepository.save(existing);
            return ResponseEntity.ok("Customer updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Customer ID not found");
    }

    public ResponseEntity<Object> updateACustomerByIdPartially(int custId, Customer cust) {
        Optional<Customer> optional = customerRepository.findById(custId);
        if(optional.isPresent()) {
            Customer existing = optional.get();
            if(cust.getFirstName() != null) existing.setFirstName(cust.getFirstName());
            if(cust.getLastName() != null) existing.setLastName(cust.getLastName());
            if(cust.getPhoneNumber() != null) existing.setPhoneNumber(cust.getPhoneNumber());
            if(cust.getEmailId() != null) existing.setEmailId(cust.getEmailId());
            customerRepository.save(existing);
            return ResponseEntity.ok("Customer partially updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Customer ID not found");
    }
}
