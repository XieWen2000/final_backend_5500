package org.example.final_backend_5500.model;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
}
