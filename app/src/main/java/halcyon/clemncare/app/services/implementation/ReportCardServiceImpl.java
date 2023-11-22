package halcyon.clemncare.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.repositories.ReportCardRepository;
import halcyon.clemncare.app.services.ReportCardService;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    @Autowired
    ReportCardRepository reportCardRepository;

    @Override
    public String createReportCard(ReportCard reportCard) {
        reportCardRepository.save(reportCard);
        return "Report Card Saved Successfully";
    }

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
