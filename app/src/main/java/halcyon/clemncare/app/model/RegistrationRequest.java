package halcyon.clemncare.app.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class RegistrationRequest {
    
    private Child child;
    private Set<Guardian> guardians = new HashSet<>();
    private Guardian emergencyContact;
}
