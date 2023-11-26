package halcyon.clemncare.app.services.implementation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.model.RegistrationRequest;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.response.ResponseHandler;
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
    public ResponseEntity<Object> registerChild(RegistrationRequest request) {

        try {

            // Save HomeAddresses of Guardians, Assign them to Guardians, then Save Guardians
            Set<Guardian> savedGuardians = new HashSet<>();
            for (Guardian guardian : request.getGuardians()) {
            HomeAddress guardianHomeAddress = guardian.getHomeAddress();
            HomeAddress savedGuardianHomeAddress = saveOrRetrieveHomeAddress(guardianHomeAddress);

            guardian.setHomeAddress(savedGuardianHomeAddress);
            savedGuardians.add(guardianRepository.save(guardian));
        }

            // Save Emergency Contact Home Address, Assign it to Contact, then Save Contact
            Guardian emergencyContact = request.getEmergencyContact();
            HomeAddress emergencyContactHomeAddress = emergencyContact.getHomeAddress();
            HomeAddress savedEmergencyContactHomeAddress = saveOrRetrieveHomeAddress(emergencyContactHomeAddress);

            emergencyContact.setHomeAddress(savedEmergencyContactHomeAddress);
            Guardian savedEmergencyContact = guardianRepository.save(emergencyContact);

            // Save Child
            Child child = request.getChild();
            child.setGuardians(savedGuardians);
            child.setEmergencyContact(savedEmergencyContact);
            childRepository.save(child);

            return ResponseHandler.responseBuilder("Registration successful", HttpStatus.CREATED, childRepository.save(child));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.responseBuilder("Error During Registration", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    private HomeAddress saveOrRetrieveHomeAddress(HomeAddress homeAddress) {
        // Check if the HomeAddress already exists in the database
        Optional<HomeAddress> existingHomeAddress = homeAddressRepository.findByStreetAddressAndCityAndStateAndZipCode(
                homeAddress.getStreetAddress(),
                homeAddress.getCity(),
                homeAddress.getState(),
                homeAddress.getZipCode()
        );
    
        return existingHomeAddress.orElseGet(() -> homeAddressRepository.save(homeAddress));
    }
}
