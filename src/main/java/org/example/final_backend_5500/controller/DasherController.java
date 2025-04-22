package org.example.final_backend_5500.controller;


import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.dto.DasherInfoResponse;
import org.example.final_backend_5500.dto.LoginRequest;
import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.service.CustomerService;
import org.example.final_backend_5500.service.DasherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashers")
@RequiredArgsConstructor
public class DasherController {
    public final DasherService dasherService;

    @PostMapping("/signup")
    public ResponseEntity<DasherInfoResponse> createDasher(@RequestBody Dasher dasher) {
        DasherInfoResponse createdDasher = dasherService.createDasher(dasher);
        return new ResponseEntity<>(createdDasher, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<DasherInfoResponse> loginDasher(@RequestBody LoginRequest loginRequest) {
        DasherInfoResponse loggedInDasher = dasherService.loginDasher(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(loggedInDasher);
    }

}
