package org.example.final_backend_5500.service;

import org.example.final_backend_5500.model.Customer;
import org.example.final_backend_5500.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Email is already in use"
            );
        }
        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Phone number is already in use"
            );
        }

        return customerRepository.save(customer);
    }
}
