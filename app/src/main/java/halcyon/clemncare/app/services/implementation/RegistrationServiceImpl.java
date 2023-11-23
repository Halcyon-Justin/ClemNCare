package halcyon.clemncare.app.services.implementation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.RegistrationDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.services.RegistrationService;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private HomeAddressRepository homeAddressRepository;

    @Override
    @Transactional
    public ResponseEntity<String> registerChildWithGuardianAndAddress(RegistrationDTO registrationDTO) {

        //TODO
        // Add Enums and List types from Request

        // Extract information from the DTO

        //Child Info
        String childFirstName = registrationDTO.getChildFirstName();
        String childLastName = registrationDTO.getChildLastName();
        LocalDate childDateOfBirth = registrationDTO.getChildDateOfBirth();

        //Guardian Info
        String guardianFirstName = registrationDTO.getGuardianFirstName();
        String guardianLastName = registrationDTO.getGuardianLastName();
        String guardianPhoneNumber = registrationDTO.getGuardianPhoneNumber();
        String guardianEmailAddress = registrationDTO.getGuardianEmailAddress();

        //Guardian Address Info
        String streetAddress = registrationDTO.getStreetAddress();
        String city = registrationDTO.getCity();
        String zipCode = registrationDTO.getZipCode();

        //Emergency Contact Info
        String emergencyContactFirstName = registrationDTO.getEmergencyContactFirstName();
        String emergencyContactLastName = registrationDTO.getEmergencyContactLastName();
        String emergencyContactPhoneNumber = registrationDTO.getEmergencyContactPhoneNumber();
        String emergencyContactEmailAddress = registrationDTO.getEmergencyContactEmailAddress();


        // Save the Address first to generate an ID
        HomeAddress address = new HomeAddress();
        address.setStreetAddress(streetAddress);
        address.setCity(city);
        //ADD STATE ENUM
        address.setZipCode(zipCode);
        HomeAddress savedAddress = homeAddressRepository.save(address);

        // Save the Guardian
        Guardian guardian = new Guardian();
        guardian.setFirstName(guardianFirstName);
        guardian.setLastName(guardianLastName);
        guardian.setPhoneNumber(guardianPhoneNumber);
        guardian.setEmailAddress(guardianEmailAddress);
        guardian.setHomeAddress(savedAddress);
        Guardian savedGuardian = guardianRepository.save(guardian);

        // Save Emergency Contact
        Guardian emergencyContact = new Guardian();
        emergencyContact.setFirstName(emergencyContactFirstName);
        emergencyContact.setLastName(emergencyContactLastName);
        emergencyContact.setPhoneNumber(emergencyContactPhoneNumber);
        emergencyContact.setEmailAddress(emergencyContactEmailAddress);
        emergencyContact.setIsEmergencyContact(true);
        Guardian savedEmergencyContact = guardianRepository.save(emergencyContact);

        // Save the Child
        Child child = new Child();
        child.setFirstName(childFirstName);
        child.setLastName(childLastName);
        child.setDateOfBirth(childDateOfBirth);
        //GRAB AND SET ALLERGIES
        child.setEmergencyContact(savedEmergencyContact);
        child.addGuardianToChild(savedGuardian); // Set the saved Guardian in the Child
        childRepository.save(child);

        return ResponseEntity.ok("Registration successful");
    }
}
