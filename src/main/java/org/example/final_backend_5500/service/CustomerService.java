package org.example.final_backend_5500.service;

import org.example.final_backend_5500.dto.CustomerInfoResponse;
import org.example.final_backend_5500.dto.LoginRequest;
import org.example.final_backend_5500.model.Customer;
import org.example.final_backend_5500.model.PaymentInfo;
import org.example.final_backend_5500.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//User Signup
    public CustomerInfoResponse createCustomer(Customer customer) {
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

        Customer createdCustomer = customerRepository.save(customer);
        return new CustomerInfoResponse(createdCustomer);
    }

    public CustomerInfoResponse login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Email does not exist"
            );
        }
        if (!customer.getPassword().equals(password)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid password"
            );
        }
        return new CustomerInfoResponse(customer);
    }

    public CustomerInfoResponse findCustomerById(String id) {
        return customerRepository.findById(id)
                .map(CustomerInfoResponse::new)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"
                ));
    }

    public CustomerInfoResponse updateCustomerAccountInfo(String id, Customer updatedData) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);

        if (foundCustomer.isPresent()) {
            if (!Objects.equals(foundCustomer.get().getEmail(), updatedData.getEmail()) && customerRepository.existsByEmail(updatedData.getEmail())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Email is already in use"
                );
            }
            if (!Objects.equals(foundCustomer.get().getPhone(), updatedData.getPhone()) && customerRepository.existsByPhone(updatedData.getPhone())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Phone number is already in use"
                );
            }
            Customer customer = foundCustomer.get();
            customer.setFirstName(updatedData.getFirstName());
            customer.setLastName(updatedData.getLastName());
            customer.setPhone(updatedData.getPhone());
            customer.setEmail(updatedData.getEmail());
            customer.setAddress(updatedData.getAddress());
            Customer updatedCustomer = customerRepository.save(customer);
            return new CustomerInfoResponse(updatedCustomer);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Customer not found"
            );
        }
    }

    public CustomerInfoResponse addPaymentMethod(String customerId, PaymentInfo updatedData) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"
                ));
        if (customer.getPaymentInfo() == null) {
            customer.setPaymentInfo(new ArrayList<>());
        }
        customer.getPaymentInfo().add(updatedData);
        Customer updatedCustomer = customerRepository.save(customer);
        return new CustomerInfoResponse(updatedCustomer);
    }

    public CustomerInfoResponse deletePaymentMethod(String customerId, PaymentInfo paymentInfo) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"
                ));

        if (customer.getPaymentInfo() == null || !customer.getPaymentInfo().remove(paymentInfo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Payment method not found"
            );
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return new CustomerInfoResponse(updatedCustomer);
    }
}
