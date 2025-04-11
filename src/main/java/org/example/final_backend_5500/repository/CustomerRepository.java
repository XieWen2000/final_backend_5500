package org.example.final_backend_5500.repository;

import org.example.final_backend_5500.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
    Customer findById(String id);
    Customer findByEmail(String email);
    Customer findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
