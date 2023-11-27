package halcyon.clemncare.app.services;

import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.RegistrationRequest;

public interface RegistrationService {
    public Family registerNewFamily(RegistrationRequest request);
}




