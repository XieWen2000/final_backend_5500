package org.example.final_backend_5500.controller;


import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.dto.CustomerInfoResponse;
import org.example.final_backend_5500.dto.LoginRequest;
import org.example.final_backend_5500.dto.RestaurantInfoResponse;
import org.example.final_backend_5500.model.Customer;
import org.example.final_backend_5500.model.PaymentInfo;
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
    public ResponseEntity<CustomerInfoResponse> createCustomer(@RequestBody Customer customer) {
        CustomerInfoResponse createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerInfoResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        CustomerInfoResponse customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInfoResponse> getCustomerById(@PathVariable String id) {
        CustomerInfoResponse customer = customerService.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<CustomerInfoResponse> updateCustomerAccountInfo(@PathVariable String id, @RequestBody Customer updatedData) {
        CustomerInfoResponse updatedCustomer = customerService.updateCustomerAccountInfo(id, updatedData);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PostMapping("/payments/{customerId}")
    public ResponseEntity<CustomerInfoResponse> addPaymentMethod(@PathVariable String customerId, @RequestBody PaymentInfo updatedData) {
        CustomerInfoResponse updatedCustomer = customerService.addPaymentMethod(customerId, updatedData);
        return ResponseEntity.ok(updatedCustomer);
    }
    @DeleteMapping("/payments/{customerId}")
    public ResponseEntity<CustomerInfoResponse> deletePaymentMethod(@PathVariable String customerId, @RequestBody PaymentInfo paymentInfo) {
        CustomerInfoResponse updatedCustomer = customerService.deletePaymentMethod(customerId, paymentInfo);
        return ResponseEntity.ok(updatedCustomer);
    }
}
