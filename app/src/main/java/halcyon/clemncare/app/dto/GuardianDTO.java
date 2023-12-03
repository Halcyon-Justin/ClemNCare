package halcyon.clemncare.app.dto;

import halcyon.clemncare.app.enums.Relationship;
import lombok.Data;

@Data
public class GuardianDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private FamilyDTO family;
    private Relationship relationship;
    private boolean isEmergencyContact;

}


