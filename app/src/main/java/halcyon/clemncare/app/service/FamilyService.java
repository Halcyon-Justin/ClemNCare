package halcyon.clemncare.app.service;

import java.util.List;

import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;

public interface FamilyService {
    public Family createFamily(FamilyDTO familyDTO);

    public Family updateFamily(Long id, FamilyDTO familyDTO);

    // public Family partialUpdateFamily(Long id, FamilyDTO familyDTO);

    public void deleteFamily(Long familyId);

    public Family getFamily(Long familyId);

    public List<Family> getAllFamilies();

    public List<Child> getActiveChildrenFromFamilyId(Long familyId);
    
}
