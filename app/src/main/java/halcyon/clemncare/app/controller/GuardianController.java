package halcyon.clemncare.app.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.services.GuardianService;

@RestController
@RequestMapping("/api/guardian")
public class GuardianController {

    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }
    
    @GetMapping("/")
    public List<Guardian> getGuardians() {
        return guardianService.getAllGuardians();
    }

    @GetMapping("/{id}")
    public Guardian getGuardian(@PathVariable("id") Long id) {
        return guardianService.getGuardian(id);
    }

    @PostMapping
    public String createGuardian(@RequestBody Guardian guardian) {
        return guardianService.createGuardian(guardian);
    }

    @PutMapping("/{id}")
    public String updateGuardian(@RequestBody Guardian guardian) {
        return guardianService.updateGuardian(guardian);
    }

    @DeleteMapping("/{id}")
    public String deleteGuardian(@PathVariable("id") Long id) {
        return guardianService.deleteGuardian(id);
    }

    @GetMapping("/find/{id}")
    public Set<Child> findChildrenByGuardianId(@PathVariable("id") Long id) {
        return guardianService.findChildrenByGuardianId(id);
    }
}
