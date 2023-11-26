package halcyon.clemncare.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.services.FamilyService;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    FamilyRepository familyRepository;

    @Override
    public String createFamily(Family family) {
        familyRepository.save(family);
        return "Family Saved Successfully";
    }

    @Override
    public String updateFamily(Family family) {
        familyRepository.save(family);
        return "Family Updated Successfully";
    }

    @Override
    public String deleteFamily(Long familyId) {
        familyRepository.deleteById(familyId);
        return "Family Deleted Successfully";
    }

    @Override
    public Family getFamily(Long familyId) {
       return familyRepository.findById(familyId).get();
    
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }
    
}
