package halcyon.clemncare.app.services;

import java.util.List;

import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.model.ReportCardRequest;

public interface ReportCardService {
    public ReportCard createReportCard(ReportCardRequest reportCardRequest);

    public String updateReportCard(ReportCard reportCard);

    public String deleteReportCard(Long reportCardId);

    public ReportCard getReportCard(Long reportCardId);

    public List<ReportCard> getAllReportCardsByChildId(Long id);

}
