package halcyon.clemncare.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.services.GuardianService;

@Service
public class GuardianServiceImpl implements GuardianService{

    @Autowired
    GuardianRepository guardianRepository;

    @Override
    public String createGuardian(Guardian guardian) {
        guardianRepository.save(guardian);
        return "Guardian Saved Successfully";
    }

    @Override
    public String updateGuardian(Guardian guardian) {
        guardianRepository.save(guardian);
        return "Guardian Updated Successfully";
    }

    @Override
    public String deleteGuardian(Long guardianId) {
        guardianRepository.deleteById(guardianId);
        return "Guardian Deleted Successfully";
    }

    @Override
    public Guardian getGuardian(Long guardianId) {
        return guardianRepository.findById(guardianId).get();
    }

    @Override
    public List<Guardian> getGuardians() {
        return guardianRepository.findAll();
    }

}
