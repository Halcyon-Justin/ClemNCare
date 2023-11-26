package halcyon.clemncare.app.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private List<String> allergies;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> frequency;

    @OneToMany(mappedBy = "child")
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
}
