package halcyon.clemncare.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.services.ReportCardService;

@RestController
@RequestMapping("/api/reportcards")
public class ReportCardController {

    private final ReportCardService reportCardService;

    public ReportCardController(ReportCardService reportCardService) {
        this.reportCardService = reportCardService;
    }

    @GetMapping("/{id}")
    public ReportCard getReportCard(@PathVariable("id") Long id) {
        return reportCardService.getReportCard(id);
    }

    @PostMapping
    public String createReportCard(@RequestBody ReportCard reportCard) {
        return reportCardService.createReportCard(reportCard);
    }

    @PutMapping("/{id}")
    public String updateReportCard(@RequestBody ReportCard reportCard) {
        return reportCardService.updateReportCard(reportCard);
    }

    @DeleteMapping("/{id}")
    public String deleteReportCard(@PathVariable("id") Long id) {
        return reportCardService.deleteReportCard(id);
    }

    @GetMapping("/find/{id}")
    public List<ReportCard> findChildrenByReportCardId(@PathVariable("id") Long id) {
        return reportCardService.getAllReportCardsByChildId(id);
    }
   
    
}
