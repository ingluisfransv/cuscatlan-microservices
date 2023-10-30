package com.cuscatlan.test.shoppingcartapi.controller;

import com.cuscatlan.test.shoppingcartapi.model.Customer;
import com.cuscatlan.test.shoppingcartapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  ResponseEntity<Customer>  createCustomer(@RequestBody Customer customer, UriComponentsBuilder uriBuilder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity
                .created(uriBuilder.path("/customers/{id}").buildAndExpand(createdCustomer.getId()).toUri())
                .headers(headers)
                .body(createdCustomer);
    }

    @RequestMapping(value = "{id}/getCustomerInfo", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerInfo(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer customer = customerService.getCustomerInfo(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updatedCustomer, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Customer customerDeleted = customerService.deleteCustomer(id);
        return new ResponseEntity<>(customerDeleted, headers, HttpStatus.NO_CONTENT);
    }
}
