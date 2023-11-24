package halcyon.clemncare.app.mappers;

import org.springframework.stereotype.Component;

import halcyon.clemncare.app.dto.RegistrationDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;

@Component
public class RegistrationMapper {

    public HomeAddress mapAddress(RegistrationDTO registrationDTO) {

        HomeAddress address = new HomeAddress();
        address.setStreetAddress(registrationDTO.getStreetAddress());
        address.setCity(registrationDTO.getCity());
        address.setZipCode(registrationDTO.getZipCode());
        return address;
    }

    public Guardian mapGuardian(RegistrationDTO registrationDTO, HomeAddress address) {
        
        Guardian guardian = new Guardian();
        guardian.setFirstName(registrationDTO.getGuardianFirstName());
        guardian.setLastName(registrationDTO.getGuardianLastName());
        guardian.setPhoneNumber(registrationDTO.getGuardianPhoneNumber());
        guardian.setEmailAddress(registrationDTO.getGuardianEmailAddress());
        guardian.setRelationship(registrationDTO.getGuardianRelationship());
        guardian.setHomeAddress(address);
        return guardian;
    }

    public Guardian mapEmergencyContact(RegistrationDTO registrationDTO) {

        Guardian emergencyContact = new Guardian();
        emergencyContact.setFirstName(registrationDTO.getEmergencyContactFirstName());
        emergencyContact.setLastName(registrationDTO.getEmergencyContactLastName());
        emergencyContact.setPhoneNumber(registrationDTO.getEmergencyContactPhoneNumber());
        emergencyContact.setEmailAddress(registrationDTO.getEmergencyContactEmailAddress());
        emergencyContact.setIsEmergencyContact(true);
        return emergencyContact;
    }

    public Child mapChild(RegistrationDTO registrationDTO, Guardian guardian, Guardian emergencyContact) {

        Child child = new Child();
        child.setFirstName(registrationDTO.getChildFirstName());
        child.setLastName(registrationDTO.getChildLastName());
        child.setDateOfBirth(registrationDTO.getChildDateOfBirth());
        child.setEmergencyContact(emergencyContact);
        child.addGuardianToChild(guardian);
        child.setFrequency(registrationDTO.getChildFrequency());
        return child;
    }
    
}
