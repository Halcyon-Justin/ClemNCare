package halcyon.clemncare.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import halcyon.clemncare.app.enums.Relationship;
import lombok.Data;

@Data
@Entity
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Relationship relationship;

    @OneToOne(cascade = CascadeType.ALL)
    private HomeAddress address;

    public String getname() {
        return firstName + " " + lastName;
    }

    // Constructors, getters, and setters
}