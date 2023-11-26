package halcyon.clemncare.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.services.GuardianService;

@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    @Autowired
    private GuardianService guardianService;

    @GetMapping("/")
    public ResponseEntity<Object> getGuardians() {
        return ResponseHandler.responseBuilder("Requested All Guardian Data", HttpStatus.OK,
                guardianService.getAllGuardians());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGuardian(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Requested Specific Guardian Data", HttpStatus.OK,
                guardianService.getGuardian(id));
    }

    @PostMapping
    public ResponseEntity<Object> createGuardian(@RequestBody Guardian guardian) {
        return ResponseHandler.responseBuilder("Guardian Created Successfully", HttpStatus.CREATED,
                guardianService.createGuardian(guardian));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGuardian(@RequestBody Guardian guardian) {
        return ResponseHandler.responseBuilder("Guardian Updated Successfully", HttpStatus.OK,
                guardianService.updateGuardian(guardian));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuardian(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Guardian Deleted Successfully", HttpStatus.OK,
                guardianService.deleteGuardian(id));
    }

}
