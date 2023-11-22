package halcyon.clemncare.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "report_card")
public class ReportCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_card_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    private Boolean hasNapped;
    private String notes;
    private String sendTo;
    private Date reportCardDate;

}
