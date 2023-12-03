package halcyon.clemncare.app.service;

import java.util.Date;
import java.util.List;

import halcyon.clemncare.app.model.Invoice;

public interface InvoiceService {

    public Invoice createInvoice(Long familyId);

    public String updateInvoice(Invoice invoice);

    public String deleteInvoice(Long invoiceId);

    public Invoice getInvoice(Long invoiceId);

    public List<Invoice> getAllInvoices();

    public List<Invoice> findInvoicesByDueDate(Date dueDate);

    public List<Invoice> findInvoicesByFamilyId(Long familyId);
    
}
