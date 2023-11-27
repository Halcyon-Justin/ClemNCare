package halcyon.clemncare.app.model;

import lombok.Data;

@Data
public class ReportCardRequest {

    private Long childId;
    private Boolean hasNapped;
    private String notes;   
}
