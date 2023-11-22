package halcyon.clemncare.app.services;

import java.util.List;
import java.util.Set;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;

public interface ChildService {
    public String createChild(Child child);

    public String updateChild(Child child);

    public String deleteChild(Long childId);

    public Child getChild(Long childId);

    public List<Child> getAllChildren();

    public Set<Guardian> findGuardiansByChildId(Long childId);
}
