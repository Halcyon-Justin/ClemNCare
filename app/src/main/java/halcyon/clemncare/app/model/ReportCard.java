package halcyon.clemncare.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table(name = "report_card")
public class ReportCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id")

    private Boolean hasNapped;
    private String notes;
    private String sendTo;
    private Date reportCardDate;

}
