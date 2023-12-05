package halcyon.clemncare.app.model;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import halcyon.clemncare.app.listeners.EntityListener;

@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class TimeStampedEntity {

    private Date createdDate;
    private Date updateDate;

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date upDateDate) {
        this.updateDate = upDateDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
