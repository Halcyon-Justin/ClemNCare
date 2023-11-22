package halcyon.clemncare.app.services;

import java.util.List;
import java.util.Set;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;

public interface GuardianService {
    public String createGuardian(Guardian guardian);

    public String updateGuardian(Guardian guardian);

    public String deleteGuardian(Long guardianId);

    public Guardian getGuardian(Long guardianId);

    public List<Guardian> getAllGuardians();

    public Set<Child> findChildrenByGuardianId(Long id);

}
