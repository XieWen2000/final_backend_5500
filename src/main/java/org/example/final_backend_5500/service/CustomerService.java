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
        System.out.println("Login request: " + request);
        Customer customer = customerRepository.findByEmail(request.getEmail());
        System.out.println("Customer: " + customer);
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
        CustomerInfoResponse response = new CustomerInfoResponse();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setAddresses(customer.getAddress());
        response.setPhone(customer.getPhone());
        return response;
    }


}
