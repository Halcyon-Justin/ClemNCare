package halcyon.clemncare.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
    private LocalDate dateOfBirth;

    @ElementCollection
    private List<String> allergies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact_id", referencedColumnName = "guardian_id")
    private Guardian emergencyContact;

    @ManyToMany
    @JoinTable(name = "child_guardian", joinColumns = @JoinColumn(name = "child_id"), inverseJoinColumns = @JoinColumn(name = "guardian_id"))
    private Set<Guardian> guardians = new HashSet<>();

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

    public int getAge() {
        LocalDate now = LocalDate.now();
        return now.getYear() - dateOfBirth.getYear();
    }

    public void addGuardianToChild(Guardian guardian) {
        this.guardians.add(guardian);
        guardian.getChildren().add(this);
    }

    @Override
    public int hashCode() {
        // Exclude guardians to avoid circular reference
        return Objects.hash(id, firstName, lastName, dateOfBirth, allergies, frequency, isActive, notes,
                emergencyContact, reportCards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Child child = (Child) o;
        return Objects.equals(id, child.id) &&
                Objects.equals(firstName, child.firstName) &&
                Objects.equals(lastName, child.lastName) &&
                Objects.equals(dateOfBirth, child.dateOfBirth) &&
                Objects.equals(allergies, child.allergies) &&
                Objects.equals(emergencyContact, child.emergencyContact) &&
                Objects.equals(frequency, child.frequency) &&
                Objects.equals(reportCards, child.reportCards) &&
                Objects.equals(isActive, child.isActive) &&
                Objects.equals(notes, child.notes);
    }
}
