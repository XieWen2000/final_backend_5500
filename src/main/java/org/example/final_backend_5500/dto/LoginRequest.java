package org.example.final_backend_5500.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
