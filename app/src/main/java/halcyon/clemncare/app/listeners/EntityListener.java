package halcyon.clemncare.app.listeners;

import java.util.Date;

import javax.persistence.PrePersist;

import halcyon.clemncare.app.model.TimeStampedEntity;

public class EntityListener {

    @PrePersist
    public void beforePersist(TimeStampedEntity entity) {
        entity.setCreatedDate(new Date());
    }
    
}
