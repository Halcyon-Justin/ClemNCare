package halcyon.clemncare.app.mappers;

import org.springframework.stereotype.Component;

import halcyon.clemncare.app.dto.RegistrationDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;

@Component
public class DefaultRegistrationMapper implements RegistrationMapper {

    @Override
    public HomeAddress mapAddress(RegistrationDTO registrationDTO) {

        if (registrationDTO == null) {
            return null;
        }

        HomeAddress address = new HomeAddress();
        address.setStreetAddress(registrationDTO.getStreetAddress());
        address.setCity(registrationDTO.getCity());
        address.setZipCode(registrationDTO.getZipCode());
        return address;
    }

    @Override
    public Guardian mapGuardian(RegistrationDTO registrationDTO, HomeAddress address) {
        
        if (registrationDTO == null) {
            return null;
        }

        Guardian guardian = new Guardian();
        guardian.setFirstName(registrationDTO.getGuardianFirstName());
        guardian.setLastName(registrationDTO.getGuardianLastName());
        guardian.setPhoneNumber(registrationDTO.getGuardianPhoneNumber());
        guardian.setEmailAddress(registrationDTO.getGuardianEmailAddress());
        guardian.setRelationship(registrationDTO.getGuardianRelationship());
        guardian.setHomeAddress(address);
        return guardian;
    }

    @Override
    public Guardian mapEmergencyContact(RegistrationDTO registrationDTO) {

        if (registrationDTO == null) {
            return null;
        }

        Guardian emergencyContact = new Guardian();
        emergencyContact.setFirstName(registrationDTO.getEmergencyContactFirstName());
        emergencyContact.setLastName(registrationDTO.getEmergencyContactLastName());
        emergencyContact.setPhoneNumber(registrationDTO.getEmergencyContactPhoneNumber());
        emergencyContact.setEmailAddress(registrationDTO.getEmergencyContactEmailAddress());
        emergencyContact.setIsEmergencyContact(true);
        return emergencyContact;
    }

    @Override
    public Child mapChild(RegistrationDTO registrationDTO, Guardian guardian, Guardian emergencyContact) {

        if (registrationDTO == null) {
            return null;
        }

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
