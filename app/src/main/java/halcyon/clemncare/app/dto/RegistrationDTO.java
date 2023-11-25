package halcyon.clemncare.app.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import halcyon.clemncare.app.enums.DayOfWeek;
import halcyon.clemncare.app.enums.Relationship;
import halcyon.clemncare.app.enums.StateCode;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.model.ReportCard;
import lombok.Data;

@Data
public class RegistrationDTO {

    // Child details
    private String childFirstName;
    private String childLastName;
    private LocalDate childDateOfBirth;
    private List<String> childAllergies;
    private Guardian emergencyContact;
    private List<Guardian> guardians;
    private List<DayOfWeek> childFrequency;
    private List<ReportCard> reportCards;
    private Boolean childIsActive;
    private String childNotes;

    // Guardian details
    private String guardianFirstName;
    private String guardianLastName;
    private String guardianPhoneNumber;
    private String guardianEmailAddress;
    private Relationship guardianRelationship;
    private Set<Child> guardianChildren;
    private HomeAddress guardianHomeAddress;

    // Emergency Contact details
    private String emergencyContactFirstName;
    private String emergencyContactLastName;
    private String emergencyContactPhoneNumber;
    private String emergencyContactEmailAddress;
    private Relationship emergencyContactRelationship;
    private Set<Child> emergencyContactChildren;
    private HomeAddress emergencyContactHomeAddress;
    private Boolean isEmergencyContact;

    // HomeAddress details
    private String streetAddress;
    private String city;
    private StateCode state;
    private String zipCode;

    // ReportCard details
    private Boolean reportCardHasNapped;
    private String reportCardNotes;
    private String reportCardSendTo;
    private Date reportCardDate;

}
