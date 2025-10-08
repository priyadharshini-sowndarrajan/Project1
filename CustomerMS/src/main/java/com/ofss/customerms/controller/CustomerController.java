package com.ofss.customerms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofss.customerms.model.Customer;
import com.ofss.customerms.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> listAllCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getACustomerById(@PathVariable("id") int custId) {
        return customerService.getACustomerById(custId);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addACustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") int custId) {
        return customerService.deleteACustomerById(custId);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Object> updateACustomer(@PathVariable("id") int custId,
                                                  @RequestBody Customer cust) {
        return customerService.updateACustomerById(custId, cust);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<Object> updateACustomerPartially(@PathVariable("id") int custId,
                                                           @RequestBody Customer cust) {
        return customerService.updateACustomerByIdPartially(custId, cust);
    }
}
