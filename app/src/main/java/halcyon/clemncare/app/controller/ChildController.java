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

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.services.ChildService;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/")
    public List<Child> getChildren() {
        return childService.getAllChildren();
    }

    @GetMapping("/{id}")
    public Child getChild(@PathVariable("id") Long id) {
        return childService.getChild(id);
    }

    @PostMapping
    public String createChild(@RequestBody Child child) {
        return childService.createChild(child);
    }

    @PutMapping("/{id}")
    public String updateChild(@RequestBody Child child) {
        return childService.updateChild(child);
    }

    @DeleteMapping("/{id}")
    public String deleteChild(@PathVariable("id") Long id) {
        return childService.deleteChild(id);
    }

}