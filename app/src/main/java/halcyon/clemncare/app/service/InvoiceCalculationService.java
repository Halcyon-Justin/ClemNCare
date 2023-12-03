package halcyon.clemncare.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;

@Service
public class InvoiceCalculationService {

    @Autowired
    private FamilyService familyService;


    public Long calculateAmountDue(Long familyId) {
        

        List<Child> children = familyService.getActiveChildrenFromFamilyId(familyId);

        Long calculatedAmount = 0L;

        for (Child child : children) {
            int selectedDays = child.getFrequencyInNumber();

            if (child.getAge() < 2) {
                // Infants/Toddlers pay for all 5 days, regardless of selected days
                calculatedAmount += calculateDailyRate(child) * 5;
            } else {
                // Preschoolers pay based on their selected days
                calculatedAmount += calculateDailyRate(child) * selectedDays;
            }
        }

        return calculatedAmount;
    }

    private Long calculateDailyRate(Child child) {
        if (child.getAge() < 2) {
            return 60L; 
        } else {
            return 50L; 
        }
    }
}
