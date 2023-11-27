package halcyon.clemncare.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import halcyon.clemncare.app.enums.Relationship;
import lombok.Data;

@Data
@Entity
@Table(name = "guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    private Boolean isEmergencyContact =  false;

    public String getName() {
        return firstName + " " + lastName;
    }

}
