package halcyon.clemncare.app.services;

import org.springframework.http.ResponseEntity;

import halcyon.clemncare.app.dto.RegistrationRequest;

public interface RegistrationService {
    ResponseEntity<String> registerChild(RegistrationRequest request);
}




