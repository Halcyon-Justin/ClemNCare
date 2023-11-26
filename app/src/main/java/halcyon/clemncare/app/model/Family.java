package halcyon.clemncare.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private HomeAddress address;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Child> children;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Guardian> guardians;

    @OneToOne
    @JoinColumn(name = "emergency_contact_id")
    private Guardian emergencyContact;
    
}
