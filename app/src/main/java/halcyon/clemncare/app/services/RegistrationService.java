package halcyon.clemncare.app.services;

import org.springframework.http.ResponseEntity;

import halcyon.clemncare.app.model.RegistrationRequest;

public interface RegistrationService {
    ResponseEntity<Object> registerNewFamily(RegistrationRequest request);
}




