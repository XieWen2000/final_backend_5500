package org.example.final_backend_5500.service;

import org.example.final_backend_5500.dto.DasherInfoResponse;
import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.repository.DasherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DasherService {
    private final DasherRepository dasherRepository;

    @Autowired
    public DasherService(DasherRepository dasherRepository) {
        this.dasherRepository = dasherRepository;
    }

    public DasherInfoResponse createDasher(Dasher dasher) {
        if (dasherRepository.existsByEmail(dasher.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Email is already in use"
            );
        }
        if (dasherRepository.existsByPhone(dasher.getPhone())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Phone number is already in use"
            );
        }

        Dasher createdDasher = dasherRepository.save(dasher);
        return new DasherInfoResponse(createdDasher);
    }

    public DasherInfoResponse loginDasher(String email, String password) {
        Dasher dasher = dasherRepository.findByEmail(email);
        if (dasher == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Email does not exist"
            );
        }
        if (!dasher.getPassword().equals(password)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid password"
            );
        }
        return new DasherInfoResponse(dasher);
    }

    public DasherInfoResponse getDasherById(String id) {
        Dasher dasher = dasherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dasher not found"
                ));
        return new DasherInfoResponse(dasher);
    }

    public DasherInfoResponse updateDasher(String id, Dasher updatedDasher) {
        Dasher dasher = dasherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dasher not found"
                ));
        if (updatedDasher.getFirstName() != null) {
            dasher.setFirstName(updatedDasher.getFirstName());
        }
        if (updatedDasher.getLastName() != null) {
            dasher.setLastName(updatedDasher.getLastName());
        }
        if (updatedDasher.getEmail() != null) {
            dasher.setEmail(updatedDasher.getEmail());
        }
        if (updatedDasher.getPhone() != null) {
            dasher.setPhone(updatedDasher.getPhone());
        }
        return new DasherInfoResponse(dasherRepository.save(dasher));
    }
}
