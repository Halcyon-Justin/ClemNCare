package halcyon.clemncare.app.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.services.ChildService;

@Service
public class ChildServiceImpl implements ChildService{

    ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public String createChild(Child child) {
        childRepository.save(child);
        return "Child Saved Successfully";
    }

    @Override
    public String updateChild(Child child) {
        childRepository.save(child);
        return "Child Updated Successfully";
    }

    @Override
    public String deleteChild(Long childId) {
        childRepository.deleteById(childId);
        return "Child Removed Successfully";
    }

    @Override
    public Child getChild(Long childId) {
        return childRepository.findById(childId).get();
    }

    @Override
    public List<Child> getAllChildrend() {
        return childRepository.findAll();
    }
    
}
