package halcyon.clemncare.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import halcyon.clemncare.app.dto.ReportCardRequest;
import halcyon.clemncare.app.model.ReportCard;

public interface ReportCardService {
    public ReportCard createReportCard(ReportCardRequest reportCardRequest, Long id);

    public String updateReportCard(ReportCard reportCard);

    public String deleteReportCard(Long reportCardId);

    public ReportCard getReportCard(Long reportCardId);

    public Page<ReportCard> getReportCardsByChildId(Long childId, Pageable pageable);

}
