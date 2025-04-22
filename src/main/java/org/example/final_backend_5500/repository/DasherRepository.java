package org.example.final_backend_5500.repository;
import org.example.final_backend_5500.model.Dasher;
import org.example.final_backend_5500.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface DasherRepository extends MongoRepository<Dasher, String> {
    Dasher findByOrderId(String orderId);
    Dasher findByDasherId(String dasherId);
}
