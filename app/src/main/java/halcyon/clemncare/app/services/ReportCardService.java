package halcyon.clemncare.app.services;

import java.util.List;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.ReportCard;

public interface ReportCardService {
    public String createReportCard(ReportCard reportCard);
    public String updateReportCard(ReportCard reportCard);
    public String deleteReportCard(Long reportCardId);
    public ReportCard getReportCard(Long reportCardId);
    public List<ReportCard> getAllReportCardsByChildId(Child childId);
    
}
