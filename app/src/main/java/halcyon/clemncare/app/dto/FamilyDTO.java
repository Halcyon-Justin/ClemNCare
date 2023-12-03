package halcyon.clemncare.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class FamilyDTO {
    
    private Long id;
    private HomeAddressDTO address;
    private List<ChildDTO> children;
    private List<GuardianDTO> guardians;
    private GuardianDTO emergencyContact;
    private List<InvoiceDTO> invoices;
}
