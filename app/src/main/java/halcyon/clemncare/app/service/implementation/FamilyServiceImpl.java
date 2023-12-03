package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.service.FamilyService;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    FamilyRepository familyRepository;

    @Override
    public Family createFamily(FamilyDTO familyDTO) {
        Family family = new Family();
        BeanUtils.copyProperties(familyDTO, family);
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(Long id, FamilyDTO familyDTO) {
        Optional<Family> family = familyRepository.findById(id);
        if (family.isPresent()) {
            Family existingFamily = family.get();
            BeanUtils.copyProperties(familyDTO, existingFamily);
            return familyRepository.save(existingFamily);
        } else {
            throw new FamilyNotFoundException("Family with ID " + id + " not found");
        }
    }

    // @Override
    // public Family partialUpdateFamily(Long id, FamilyDTO familyDTO) {
    //     Optional<Family> family = familyRepository.findById(id);
    //     if (family.isPresent()) {
    //         Family existingFamily = family.get();
    //         BeanUtils.copyProperties(familyDTO, existingFamily);
    //         return familyRepository.save(existingFamily);
    //     } else {
    //         throw new FamilyNotFoundException("Family with ID " + id + " not found");
    //     }
    // }

    @Override
    public void deleteFamily(Long familyId) {
        familyRepository.deleteById(familyId);
    }

    @Override
    public Family getFamily(Long familyId) {
       return familyRepository.getById(familyId);
    
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public List<Child> getActiveChildrenFromFamilyId(Long familyId) {
        Family family = familyRepository.getById(familyId);
        return family.getActiveChildren();
    }
}
