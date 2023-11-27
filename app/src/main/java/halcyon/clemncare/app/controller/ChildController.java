package halcyon.clemncare.app.controller;

import java.util.List;
import java.util.Optional;

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

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.ChildService;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    @Autowired
    private ChildService childService;

    @GetMapping("/")
    public ResponseEntity<Object> getChildren() {
        return ResponseHandler.responseBuilder("Requested All Child Data", HttpStatus.OK,
                childService.getAllChildren());
    }

    @GetMapping("/find/ages/{age}")
    public ResponseEntity<Object> getChildrenByAge(@PathVariable("age") int age) {
        List<Child> children = childService.findChildrenByAge(age);

        if (children != null) {
            return ResponseHandler.responseBuilder("Children found for the specified age", HttpStatus.OK,
                    children);
        } else {
            return ResponseHandler.responseBuilder("No children found for the specified age", HttpStatus.NOT_FOUND,
                    null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getChild(@PathVariable("id") Long id) {
        Optional<Child> childOptional = Optional.ofNullable(childService.getChild(id));

        if (childOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Requested Specific Child Data", HttpStatus.OK, childOptional.get());
        } else {
            return ResponseHandler.responseBuilder("Child not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createChild(@RequestBody Child child) {
        return ResponseHandler.responseBuilder("Child Created Successfully", HttpStatus.CREATED,
                childService.createChild(child));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateChild(@RequestBody Child child) {
        return ResponseHandler.responseBuilder("Child Information Updated Successfully", HttpStatus.OK,
                childService.updateChild(child));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChild(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Child has been Deleted", HttpStatus.OK, childService.deleteChild(id));
    }

}