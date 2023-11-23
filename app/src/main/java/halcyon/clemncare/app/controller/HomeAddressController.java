package halcyon.clemncare.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.services.HomeAddressService;

@RestController
@RequestMapping("/api/homeaddresses")
public class HomeAddressController {

    private final HomeAddressService homeAddressService;

    public HomeAddressController(HomeAddressService homeAddressService) {
        this.homeAddressService = homeAddressService;
    }

    @GetMapping("/{id}")
    public HomeAddress getHomeAddress(@PathVariable("id") Long id) {
        return homeAddressService.getAddress(id);
    }

    @PostMapping
    public String createAddress(@RequestBody HomeAddress homeAddress) {
        return homeAddressService.createAddress(homeAddress);
    }

    @PutMapping("/{id}")
    public String updateAddress(@RequestBody HomeAddress homeAddress) {
        return homeAddressService.updateAddress(homeAddress);
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable("id") Long id) {
        return homeAddressService.deleteAddress(id);
    }

    @GetMapping("/find/{id}")
    public List<Guardian> findGuardianByHomeAddressId(@PathVariable("id") Long id) {
        return homeAddressService.getGuardiansById(id);
    }
    
}
