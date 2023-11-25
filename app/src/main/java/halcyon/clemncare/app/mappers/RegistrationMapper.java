package halcyon.clemncare.app.mappers;

import halcyon.clemncare.app.dto.RegistrationDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;

public interface RegistrationMapper {

    HomeAddress mapAddress(RegistrationDTO registrationDTO);
    Guardian mapGuardian(RegistrationDTO registrationDTO, HomeAddress address);
    Guardian mapEmergencyContact(RegistrationDTO registrationDTO);
    Child mapChild(RegistrationDTO registrationDTO, Guardian guardian, Guardian emergencyContact);
    
}
