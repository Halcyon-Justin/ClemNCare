package halcyon.clemncare.app.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.model.ReportCardRequest;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.ReportCardRepository;
import halcyon.clemncare.app.services.ReportCardService;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    ReportCardRepository reportCardRepository;

    @Override
    @Transactional
    public ReportCard createReportCard(ReportCardRequest reportCardRequest) {
        try {
            // Extract child information from the request
            Long childId = reportCardRequest.getChildId();

            // Retrieve the Child by ID
            Child child = childRepository.getById(childId);

            Family family = child.getFamily();
            List<String> guardianEmails = family.getGuardians().stream()
                    .filter(guardian -> Boolean.FALSE.equals(guardian.getIsEmergencyContact()))
                    .map(Guardian::getEmailAddress)
                    .collect(Collectors.toList());

            // Create a new ReportCard object
            ReportCard reportCard = new ReportCard();
            reportCard.setChild(child);
            reportCard.setHasNapped(reportCardRequest.getHasNapped());
            reportCard.setNotes(reportCardRequest.getNotes());
            reportCard.setSendTo(guardianEmails);

            // Save the ReportCard
            reportCard = reportCardRepository.save(reportCard);
            return reportCard;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions as needed
            throw new RuntimeException("Error creating report card", e);
        }
    };

    @Override
    public String updateReportCard(ReportCard reportCard) {
        reportCardRepository.save(reportCard);
        return "Report Card Updated Successfully";
    }

    @Override
    public String deleteReportCard(Long reportCardId) {
        reportCardRepository.deleteById(reportCardId);
        return "Report Card Deleted Successfully";
    }

    @Override
    public ReportCard getReportCard(Long reportCardId) {
        return reportCardRepository.findById(reportCardId).get();
    }

    @Override
    public List<ReportCard> getAllReportCardsByChildId(Long childId) {
        return reportCardRepository.findByChildId(childId);
    }

}
