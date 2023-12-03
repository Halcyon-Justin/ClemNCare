package halcyon.clemncare.app.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import halcyon.clemncare.app.enums.DayOfWeek;
import lombok.Data;

@Data
@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @ElementCollection
    @CollectionTable(name = "child_allergies", joinColumns = @JoinColumn(name = "child_id"))
    @Column(name = "allergy")
    private List<String> allergies;

    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "child_days_of_week", joinColumns = @JoinColumn(name = "child_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private Set<DayOfWeek> frequency;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean isActive;

    private String notes;

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        return now.getYear() - dateOfBirth.getYear();
    }
}
