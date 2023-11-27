package halcyon.clemncare.app.dto;

import java.util.List;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import lombok.Data;

@Data
public class RegistrationRequest {
    
    private List<Child> children;
    private List<Guardian> guardians;
    private HomeAddress address;
    private Guardian emergencyContact;
}
