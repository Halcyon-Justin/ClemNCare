package halcyon.clemncare.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import halcyon.clemncare.app.listeners.EntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "invoice")
@EntityListeners(EntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class Invoice extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private Date dueDate;

    private Long amountDue;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean isPaid;
    
}
