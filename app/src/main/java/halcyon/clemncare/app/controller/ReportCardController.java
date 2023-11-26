package halcyon.clemncare.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.services.ReportCardService;

@RestController
@RequestMapping("/api/reportcards")
public class ReportCardController {

    @Autowired
    private ReportCardService reportCardService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getReportCard(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Report Card Created Successfully", HttpStatus.OK,
                reportCardService.getReportCard(id));
    }

    @PostMapping
    public ResponseEntity<Object> createReportCard(@RequestBody ReportCard reportCard) {
        return ResponseHandler.responseBuilder("Report Card Created Successfully", HttpStatus.CREATED,
                reportCardService.createReportCard(reportCard));
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

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findChildrenByReportCardId(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Child Found From Report Card", HttpStatus.OK,
                reportCardService.getAllReportCardsByChildId(id));
    }

}
