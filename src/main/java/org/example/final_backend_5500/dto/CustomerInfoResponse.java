package org.example.final_backend_5500.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class CustomerInfoResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Optional<String> addresses;
    private String phone;

}
