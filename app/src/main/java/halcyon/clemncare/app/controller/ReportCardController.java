package halcyon.clemncare.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.dto.ReportCardRequest;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.ReportCardService;

@RestController
@RequestMapping("/api/reportcards")
public class ReportCardController {

    @Autowired
    private ReportCardService reportCardService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReportCard(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Report Card Retrieved Successfully", HttpStatus.OK,
                reportCardService.getReportCard(id));
    }

    @PostMapping("/{childId}")
    public ResponseEntity<Object> createReportCard(@RequestBody ReportCardRequest reportCardRequest, @PathVariable("childId") Long childId) {

        if (reportCardRequest == null) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }
        try {
            ReportCard createdReportCard = reportCardService.createReportCard(reportCardRequest, childId);
            return ResponseHandler.responseBuilder(
                    "Created Report Card assigned to Child ID: " + createdReportCard.getChildId(),
                    HttpStatus.CREATED, createdReportCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.responseBuilder("Error creating report card", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReportCard(@RequestBody ReportCard reportCard) {
        return ResponseHandler.responseBuilder("Report Card Updated Successfully", HttpStatus.OK,
                reportCardService.updateReportCard(reportCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReportCard(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Report Card Delted Successfully", HttpStatus.OK,
                reportCardService.deleteReportCard(id));
    }

    @GetMapping("/find/ids/{id}")
    public ResponseEntity<Object> findAllReportCardsByChildId(@PathVariable("id") Long id, Pageable pageable) {
        return ResponseHandler.responseBuilder("Report Cards for Child ID: " + id, HttpStatus.OK,
        reportCardService.getReportCardsByChildId(id, pageable));
    }

}
