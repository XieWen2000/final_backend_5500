package org.example.final_backend_5500.service;

import org.example.final_backend_5500.dto.CustomerInfoResponse;
import org.example.final_backend_5500.dto.LoginRequest;
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

//User Signup
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

    public CustomerInfoResponse login(LoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail());
        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Email does not exist"
            );
        }
        if (!customer.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid password"
            );
        }
        return new CustomerInfoResponse(customer);
    }

    public CustomerInfoResponse findUserById(String id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found"
            );
        }
        return new CustomerInfoResponse(customer);
    }
}
