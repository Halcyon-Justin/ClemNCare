package halcyon.clemncare.app.model;

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
    private Guardian emergencyContact;

    @ManyToMany(cascade = CascadeType.ALL)
    private Guardian guardian;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> frequency;

    private Boolean isActive;

    private String notes;

    public String getName() {
        return firstName + " " + lastName;
    }
}
