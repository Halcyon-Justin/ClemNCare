package halcyon.clemncare.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import halcyon.clemncare.app.enums.Relationship;
import lombok.Data;

@Data
@Entity
@Table(name = "guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guardian_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    @ManyToMany(mappedBy = "guardians")
    private Set<Child> children = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "home_address_id")
    private HomeAddress homeAddress;

    private Boolean isEmergencyContact;

    public String getName() {
        return firstName + " " + lastName;
    }

    public void addChildtoGuardian(Child child) {
        this.children.add(child);
        child.getGuardians().add(this);
    }

}
