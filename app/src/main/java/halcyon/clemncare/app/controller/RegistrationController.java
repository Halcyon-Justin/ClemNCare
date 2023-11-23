package halcyon.clemncare.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.dto.RegistrationDTO;
import halcyon.clemncare.app.services.RegistrationService;

@RestController
@RequestMapping("/api/daycare/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> registerChild(@RequestBody RegistrationDTO request) {

        //TODO
        // Handle Lists and Parse Enums for data from Request

        return registrationService.registerChildWithGuardianAndAddress(request);
    }

}

