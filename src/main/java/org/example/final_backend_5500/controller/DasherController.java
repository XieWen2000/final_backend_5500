package org.example.final_backend_5500.controller;

import lombok.RequiredArgsConstructor;
import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.service.DasherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashers")
@RequiredArgsConstructor

public class DasherController {
    private final DasherService dasherService;

    @PostMapping
    public Dasher createDasher(@RequestBody Dasher dasher){
        Dasher createDasher = dasherService.findDasher(dasher);
        return new ResponseEntity<Dasher>(createDasher, HttpStatus.CREATED).getBody();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dasher> getDasherById(@PathVariable String dasherId){
        Dasher dasher = dasherService.findDasherById(dasherId);
        return ResponseEntity.ok(dasher);

    }



}
