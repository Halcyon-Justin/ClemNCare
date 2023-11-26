package halcyon.clemncare.app.services;

import java.util.List;

import halcyon.clemncare.app.model.Child;

public interface ChildService {
    public String createChild(Child child);

    public String updateChild(Child child);

    public String deleteChild(Long childId);

    public Child getChild(Long childId);

    public List<Child> getAllChildren();
}
