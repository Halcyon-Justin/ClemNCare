package halcyon.clemncare.app.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import halcyon.clemncare.app.enums.DayOfWeek;
import lombok.Data;

@Data
@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @ElementCollection
    private List<String> allergies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact_id", referencedColumnName = "guardian_id")
    private Guardian emergencyContact;

    @ManyToMany
    @JoinTable(
        name = "child_guardian",
        joinColumns = @JoinColumn(name = "child_id"),
        inverseJoinColumns = @JoinColumn(name = "guardian_id")
    )
    private Set<Guardian> guardians;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> frequency;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<ReportCard> reportCards;

    private Boolean isActive;

    private String notes;

    public String getName() {
        return firstName + " " + lastName;
    }
}

