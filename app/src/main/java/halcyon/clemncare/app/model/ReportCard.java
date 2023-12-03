package halcyon.clemncare.app.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import halcyon.clemncare.app.listeners.EntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "report_card")
@EntityListeners(EntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class ReportCard extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "child_id")
    private Long childId;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean hasNapped;
    private String notes;

    @ElementCollection
    @CollectionTable(name = "sendTo_Child", joinColumns = @JoinColumn(name = "child_id"))
    @Column(name = "sendTo")
    private List<String> sendTo;

}
