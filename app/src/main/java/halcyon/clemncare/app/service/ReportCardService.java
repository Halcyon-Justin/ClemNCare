package halcyon.clemncare.app.service;

import java.util.List;

import halcyon.clemncare.app.dto.ReportCardRequest;
import halcyon.clemncare.app.model.ReportCard;

public interface ReportCardService {
    public ReportCard createReportCard(ReportCardRequest reportCardRequest, Long id);

    public String updateReportCard(ReportCard reportCard);

    public String deleteReportCard(Long reportCardId);

    public ReportCard getReportCard(Long reportCardId);

    public List<ReportCard> getAllReportCardsByChildId(Long id);

}
