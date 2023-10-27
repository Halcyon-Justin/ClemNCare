package halcyon.clemncare.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import halcyon.clemncare.app.enums.DayOfWeek;
import lombok.Data;

@Data
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<String> allergies;

    @OneToOne(cascade = CascadeType.ALL)
    private EmergencyContact emergencyContact;

    @ManyToMany(cascade = CascadeType.ALL)
    private Guardian guardian;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> frequency;

    private String notes;

    public String getName() {
        return firstName + " " + lastName;
    }

    public Child(String firstName, String lastName, String dateOfBirth, EmergencyContact emergencyContact, Guardian guardian) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || dateOfBirth == null || emergencyContact == null || guardian == null) {
            throw new IllegalArgumentException("Mandatory fields must not be null or empty.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
        this.guardian = guardian;
    }
}
