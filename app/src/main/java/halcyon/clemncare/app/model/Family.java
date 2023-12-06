package halcyon.clemncare.app.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Child> children;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Guardian> guardians;

    @OneToOne
    @JoinColumn(name = "emergency_contact_id")
    private Guardian emergencyContact;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    public boolean hasActiveChildren() {
        return children != null && children.stream().anyMatch(Child::isActive);
    }

    public List<Child> getActiveChildren() {
        return children != null ? children.stream().filter(Child::isActive).toList() : Collections.emptyList();
    }

    public List<Guardian> getGuardians() {
        return guardians.stream().filter(guardian -> !guardian.isEmergencyContact()).toList();
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", address=" + address +
                ", children=" + children +
                ", guardians=" + guardians +
                ", emergencyContact=" + emergencyContact +
                '}';
    }
}
