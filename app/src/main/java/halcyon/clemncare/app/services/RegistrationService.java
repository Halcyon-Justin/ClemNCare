package halcyon.clemncare.app.services;

import org.springframework.http.ResponseEntity;

import halcyon.clemncare.app.dto.RegistrationDTO;

public interface RegistrationService {
    ResponseEntity<String> registerChildWithGuardianAndAddress(RegistrationDTO registrationDTO);
}




