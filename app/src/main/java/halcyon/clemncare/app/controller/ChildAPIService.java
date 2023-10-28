package halcyon.clemncare.app.controller;

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
public class ChildAPIService {

    Child child;

    @GetMapping("/{id}")
    public Child getChild(String id){

        return child;
        
    }

    @PostMapping
    public String creatChild(@RequestBody Child child) {

        this.child = child;
        return "Child Save successfully.";

    }

    @PutMapping("/{id}")
    public String updateChild(@RequestBody Child child){
        this.child = child;
        return "Child Update successfully.";
    }

    @DeleteMapping("/{id}")
    public String deleteChild(String id){

        this.child = null;
        return "Child Delete Successfully.";
        
    }

}