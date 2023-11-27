package halcyon.clemncare.app.dto;

import lombok.Data;

@Data
public class ReportCardRequest {
    
    private Boolean hasNapped;
    private String notes;
}
