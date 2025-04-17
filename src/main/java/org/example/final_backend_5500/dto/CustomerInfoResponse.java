package org.example.final_backend_5500.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.final_backend_5500.model.Customer;
import org.example.final_backend_5500.model.PaymentInfo;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class CustomerInfoResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private List<PaymentInfo> paymentInfo;

    public CustomerInfoResponse(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
        this.paymentInfo = customer.getPaymentInfo();
    }
}
