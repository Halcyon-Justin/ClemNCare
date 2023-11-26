package halcyon.clemncare.app.model;

import java.util.List;

import lombok.Data;

@Data
public class RegistrationRequest {
    
    private List<Child> children;
    private List<Guardian> guardians;
    private HomeAddress address;
    private Guardian emergencyContact;
}
