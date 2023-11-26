package halcyon.clemncare.app.services;

import java.util.List;

import halcyon.clemncare.app.model.Family;

public interface FamilyService {
    public String createFamily(Family family);

    public String updateFamily(Family family);

    public String deleteFamily(Long familyId);

    public Family getFamily(Long familyId);

    public List<Family> getAllFamilies();
    
}
