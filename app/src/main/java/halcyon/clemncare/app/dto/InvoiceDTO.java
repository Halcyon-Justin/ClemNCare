package halcyon.clemncare.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InvoiceDTO {
    private Long id;
    private Long familyId;
    private Date dueDate;
    private Long amountDue;
    private boolean isPaid;
}
