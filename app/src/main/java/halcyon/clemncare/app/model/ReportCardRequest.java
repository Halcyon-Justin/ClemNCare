package halcyon.clemncare.app.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReportCardRequest {

    private Long childId;
    private Boolean hasNapped;
    private String notes;
    private List<String> sendTo;
    private Date reportCardDate;
    
}
