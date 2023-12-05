package halcyon.clemncare.app.dto;

import java.util.Date;

import halcyon.clemncare.app.model.Family;
import lombok.Data;

@Data
public class InvoiceDTO {
    private Long id;
    private Family family;
    private Date dueDate;
    private Long amountDue;
    private boolean isPaid;
}
