package halcyon.clemncare.app.services.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.RegistrationRequest;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.FamilyRepository;
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

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    @Transactional
    public Family registerNewFamily(RegistrationRequest registrationRequest) {

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

        // Create Family Object
        Family family = new Family();

        // Set family_id Parameter for each object
        emergencyContact.setFamily(family);

        for (Child child : children) {
            child.setFamily(family);
        }

        for (Guardian guardian : guardians) {
            guardian.setFamily(family);
        }

        family.setAddress(address);
        family.setChildren(children);
        family.setGuardians(guardians);
        family.setEmergencyContact(emergencyContact);

        family = familyRepository.save(family);

        return family;
    }

    private void saveChildren(List<Child> children) {
        for (Child child : children) {
            childRepository.save(child);
        }
    }

    private void saveGuardians(List<Guardian> guardians) {
        for (Guardian guardian : guardians) {
            guardianRepository.save(guardian);
        }
    }

    private void saveAddress(HomeAddress address) {
        homeAddressRepository.save(address);
    }

    private void saveEmergencyContact(Guardian emergencyContact) {
        guardianRepository.save(emergencyContact);
    }
}
