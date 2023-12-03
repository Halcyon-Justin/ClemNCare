package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.Optional;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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

    @Override
    public Family partialUpdateFamily(Long id, FamilyDTO familyDTO) {
        Optional<Family> family = familyRepository.findById(id);
        if (family.isPresent()) {
            Family existingFamily = family.get();
            BeanUtils.copyProperties(familyDTO, existingFamily, getNullPropertyNames(familyDTO));
            return familyRepository.save(existingFamily);
        } else {
            throw new FamilyNotFoundException("Family with ID " + id + " not found");
        }
    }

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

    private String[] getNullPropertyNames(FamilyDTO familyDTO) {
        final BeanWrapper src = new BeanWrapperImpl(familyDTO);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
