package org.example.final_backend_5500.controller;


import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.dto.CustomerInfoResponse;
import org.example.final_backend_5500.dto.LoginRequest;
import org.example.final_backend_5500.model.Customer;
import org.example.final_backend_5500.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerInfoResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        CustomerInfoResponse customer = customerService.login(loginRequest);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInfoResponse> getCustomerById(@PathVariable String id) {
        CustomerInfoResponse customer = customerService.findUserById(id);
        return ResponseEntity.ok(customer);
    }
}
