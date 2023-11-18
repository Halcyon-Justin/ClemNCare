package halcyon.clemncare.app.model;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import halcyon.clemncare.app.enums.Relationship;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

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
    private Relationship relationship;

    @ManyToMany(mappedBy = "guardians")
    private List<Child> children;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private HomeAddress address;

    public String getName() {
        return firstName + " " + lastName;
    }

}
