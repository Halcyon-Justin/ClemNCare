package halcyon.clemncare.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import halcyon.clemncare.app.enums.Relationship;

@Data
@Entity
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private Relationship relationship;

    @OneToOne(cascade = CascadeType.ALL)
    private HomeAddress address;

    public String getName() {
        return firstName + " " + lastName;
    }

}
