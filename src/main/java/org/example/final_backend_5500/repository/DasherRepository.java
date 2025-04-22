package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Dasher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DasherRepository  extends MongoRepository<Dasher, String> {
    Dasher findByEmail(String email);
    Dasher findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
