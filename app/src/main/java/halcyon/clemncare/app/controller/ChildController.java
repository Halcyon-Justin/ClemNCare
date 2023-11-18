package halcyon.clemncare.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.model.Child;


@RestController
@RequestMapping("/children")
public class ChildController {

    //TODO: add pagination

    Child child;

    @GetMapping("/")
    public List<Child> getChildren() {
        //add calls to repository
        //query to get ALL children in a list
        return null;
    }

    @GetMapping("/{id}")
    public Child getChild(String id){
        return child;
        
    }

    @PostMapping
    public String creatChild(@RequestBody Child child) {

        this.child = child;
        return "Child Saved successfully.";

    }

    @PutMapping("/{id}")
    public String updateChild(@RequestBody Child child){
        this.child = child;
        return "Child Updated successfully.";
    }

    @DeleteMapping("/{id}")
    public String deleteChild(String id){

        this.child = null;
        return "Child Deleted Successfully.";
        
    }

}