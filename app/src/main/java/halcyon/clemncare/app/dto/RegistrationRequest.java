package halcyon.clemncare.app.dto;

import java.util.HashSet;
import java.util.Set;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;

import lombok.Data;

@Data
public class RegistrationRequest {
    
    private Child child;
    private Set<Guardian> guardians = new HashSet<>();
    private Guardian emergencyContact;
}
