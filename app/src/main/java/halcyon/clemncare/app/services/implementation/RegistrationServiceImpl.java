package halcyon.clemncare.app.services.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.RegistrationDTO;
import halcyon.clemncare.app.mappers.RegistrationMapper;
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

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    @Transactional
    public ResponseEntity<String> registerChildWithGuardianAndAddress(RegistrationDTO registrationDTO) {

        try {

            HomeAddress address = registrationMapper.mapAddress(registrationDTO);
            homeAddressRepository.save(address);

            Guardian guardian = registrationMapper.mapGuardian(registrationDTO, address);
            guardianRepository.save(guardian);

            Guardian emergencyContact = registrationMapper.mapEmergencyContact(registrationDTO);
            guardianRepository.save(emergencyContact);

            Child child = registrationMapper.mapChild(registrationDTO, guardian, emergencyContact);
            childRepository.save(child);

            
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error During Registration");
        };
        return ResponseEntity.ok("Registration successful");
    }
}
