package org.example.final_backend_5500.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.final_backend_5500.model.Customer;

import java.util.Optional;

@Data
@NoArgsConstructor
public class CustomerInfoResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Optional<String> addresses;
    private String phone;

    public CustomerInfoResponse(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.addresses = customer.getAddress();
        this.phone = customer.getPhone();
    }
}
