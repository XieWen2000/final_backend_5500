package org.example.final_backend_5500.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.final_backend_5500.model.Dasher;

@Data
@NoArgsConstructor
public class DasherInfoResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String licenseNumber;
    private String vehicleInfo;

    public DasherInfoResponse(Dasher dasher) {
        this.id = dasher.getId();
        this.email = dasher.getEmail();
        this.firstName = dasher.getFirstName();
        this.lastName = dasher.getLastName();
        this.phone = dasher.getPhone();
        this.licenseNumber = dasher.getLicenseNumber();
        this.vehicleInfo = dasher.getVehicleInfo();
    }
}
