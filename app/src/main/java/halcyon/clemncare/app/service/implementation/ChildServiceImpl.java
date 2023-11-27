package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.service.ChildService;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    ChildRepository childRepository;

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
        return childRepository.findById(childId).orElse(null);
    }

    @Override
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @Override
    public List<Child> findChildrenByAge(int age) {
        List<Child> allChildren = childRepository.findAll();

        List<Child> childrenByAge = allChildren.stream()
                .filter(child -> child.getAge() == age)
                .collect(Collectors.toList());

        return childrenByAge;
    }

}
