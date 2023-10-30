package halcyon.clemncare.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.model.ReportCard;

@RestController
@RequestMapping("/reportCards")
public class ReportCardController {

    //TODO: add pagination

    ReportCard reportCard;

    @GetMapping("/")
    public List<ReportCard> getAllReportCards() {
        
        //add calls to repository
        //query to get ALL report cards
        return null;
    }

    @GetMapping("/{id}")
    public ReportCard getReportCard(String id){
        return reportCard;
    }

    @PostMapping
    public String createReportCard(@RequestBody ReportCard reportCard){

        this.reportCard = reportCard;
        return "Report Card Saved Successfully";

    }

    @PutMapping("/{id}")
    public String updateReportCard(@RequestBody ReportCard reportCard){
        this.reportCard = reportCard;
        return "Report Card Updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteReportCard(String id){

        this.reportCard = null;
        return "Report Card Deleted Successfully";
    }

}