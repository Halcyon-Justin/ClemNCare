package halcyon.clemncare.app.services.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.model.RegistrationRequest;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.FamilyRepository;
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

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    @Transactional
    public ResponseEntity<Object> registerNewFamily(RegistrationRequest registrationRequest) {

        try {

            // Extract data from RegistrationRequest
            List<Child> children = registrationRequest.getChildren();
            List<Guardian> guardians = registrationRequest.getGuardians();
            HomeAddress address = registrationRequest.getAddress();
            Guardian emergencyContact = registrationRequest.getEmergencyContact();

            // Save individual objects
            saveChildren(children);
            saveGuardians(guardians);
            saveAddress(address);
            saveEmergencyContact(emergencyContact);

            // Create and save the Family object
            Family family = new Family();
            family.setAddress(address);
            family.setChildren(children);
            family.setGuardians(guardians);
            family.setEmergencyContact(emergencyContact);
            familyRepository.save(family);

            return ResponseEntity.ok().body("Registration Successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.responseBuilder("Error During Registration", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    private void saveChildren(List<Child> children) {
        for (Child child : children) {
            System.out.println(child.getFirstName());
            childRepository.save(child);
        }
    }

    private void saveGuardians(List<Guardian> guardians) {
        for (Guardian guardian : guardians) {
            System.out.println(guardian.getFirstName());
            guardianRepository.save(guardian);
        }
    }

    private void saveAddress(HomeAddress address) {
        System.out.println(address.getState());
        homeAddressRepository.save(address);
    }

    private void saveEmergencyContact(Guardian emergencyContact) {
        System.out.println(emergencyContact.getFirstName());
        guardianRepository.save(emergencyContact);
    }
}
