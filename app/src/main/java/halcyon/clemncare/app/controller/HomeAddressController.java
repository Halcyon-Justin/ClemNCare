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

import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.services.HomeAddressService;

@RestController
@RequestMapping("/api/homeaddresses")
public class HomeAddressController {

    @Autowired
    private HomeAddressService homeAddressService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHomeAddress(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Specific Address Found", HttpStatus.OK,
                homeAddressService.getAddress(id));
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody HomeAddress homeAddress) {
        return ResponseHandler.responseBuilder("Address Created Successfully", HttpStatus.CREATED,
                homeAddressService.createAddress(homeAddress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@RequestBody HomeAddress homeAddress) {
        return ResponseHandler.responseBuilder("Address Updated Successfully", HttpStatus.OK,
                homeAddressService.updateAddress(homeAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Address Deleted Successfully", HttpStatus.OK,
                homeAddressService.deleteAddress(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findGuardianByHomeAddressId(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Guardians Found with Specified Address", HttpStatus.OK,
                homeAddressService.getGuardiansById(id));
    }

}
