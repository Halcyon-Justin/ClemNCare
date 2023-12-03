package halcyon.clemncare.app.controller;

import java.util.Optional;

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

import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<Object> getInvoices() {
        return ResponseHandler.responseBuilder("Requested All Invoice Data", HttpStatus.OK,
                invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoice(@PathVariable("id") Long id) {
        Optional<Invoice> invoiceOptional = Optional.ofNullable(invoiceService.getInvoice(id));

        if (invoiceOptional.isPresent()) {
            return ResponseHandler.responseBuilder("Requested Specific Invoice Data", HttpStatus.OK, invoiceOptional.get());
        } else {
            return ResponseHandler.responseBuilder("Invoice not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/find/family/{familyId}")
    public ResponseEntity<Object> getInvoicesByFamilyId(@PathVariable("familyId") Long familyId) {
        return ResponseHandler.responseBuilder("Requested Specific Invoice Data", HttpStatus.OK,
                invoiceService.findInvoicesByFamilyId(familyId));
    }

    @PostMapping("/{familyId}")
    public ResponseEntity<Object> createInvoice(@PathVariable("familyId") Long familyId) {
        try {
            Invoice createdInvoice = invoiceService.createInvoice(familyId);
            return ResponseHandler.responseBuilder(
                    "Created Invoice assigned to Family ID: " + createdInvoice.getFamily().getId(),
                    HttpStatus.CREATED, createdInvoice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.responseBuilder("Error creating invoice", HttpStatus.INTERNAL_SERVER_ERROR,
                    null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@RequestBody Invoice invoice) {
        return ResponseHandler.responseBuilder("Invoice Updated Successfully", HttpStatus.OK,
                invoiceService.updateInvoice(invoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable("id") Long id) {
        return ResponseHandler.responseBuilder("Invoice Deleted Successfully", HttpStatus.OK,
                invoiceService.deleteInvoice(id));
    }
    
}
