package halcyon.clemncare.app.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import halcyon.clemncare.app.enums.TaskStatus;
import halcyon.clemncare.app.listeners.EntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "task")
@EntityListeners(EntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class Task extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private TaskStatus status;
    
}
