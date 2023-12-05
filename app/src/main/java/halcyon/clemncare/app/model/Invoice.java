package halcyon.clemncare.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import halcyon.clemncare.app.enums.InvoiceStatus;
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

    private LocalDate dueDate;

    @Column(columnDefinition = "BIGINT default 0")
    private Long amountDue;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "STRING default UNPAID")
    private InvoiceStatus status;
    
}
