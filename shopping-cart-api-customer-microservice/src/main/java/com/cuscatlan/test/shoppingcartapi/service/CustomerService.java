package com.cuscatlan.test.shoppingcartapi.service;

import com.cuscatlan.test.shoppingcartapi.model.Customer;
import com.cuscatlan.test.shoppingcartapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerInfo(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        return existingCustomer.orElse(null);
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    public Customer deleteCustomer(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer deletedCustomer = existingCustomer.get();
            customerRepository.deleteById(id);
            return deletedCustomer;
        } else {
            return null;
        }
    }
}
