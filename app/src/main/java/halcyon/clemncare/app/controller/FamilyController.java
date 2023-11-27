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

import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.FamilyService;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @GetMapping("/")
    public ResponseEntity<Object> getFamilies() {
        return ResponseHandler.responseBuilder("Requested All Family Data", HttpStatus.OK,
                familyService.getAllFamilies());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getFamily(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Requested Specific Family Data", HttpStatus.OK,
                familyService.getFamily(id));
    }

    @PostMapping
    public ResponseEntity<Object> createFamily(@RequestBody Family family) {
        return ResponseHandler.responseBuilder("Family Created Successfully", HttpStatus.CREATED,
                familyService.createFamily(family));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFamily(@RequestBody Family family) {
        return ResponseHandler.responseBuilder("Family Updated Successfully", HttpStatus.OK,
                familyService.updateFamily(family));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFamily(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Family Deleted Successfully", HttpStatus.OK,
                familyService.deleteFamily(id));
    }
 
}
