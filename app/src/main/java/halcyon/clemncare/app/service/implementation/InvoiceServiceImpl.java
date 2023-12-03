package halcyon.clemncare.app.service.implementation;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.InvoiceRepository;
import halcyon.clemncare.app.service.InvoiceCalculationService;
import halcyon.clemncare.app.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    InvoiceCalculationService invoiceCalculationService;

    @Override
    @Transactional
    public Invoice createInvoice(Long familyId) {
        try{
            //Find Associated Family (Should already be saved/created)
            Family family = familyRepository.getById(familyId);
            //Calulate Amount Due for Family based on # of kids, age, and frquency
            Long calculatedAmountDue = invoiceCalculationService.calculateAmountDue(familyId);


            //Create new Invoice and Set params
            Invoice invoice = new Invoice();
            invoice.setFamily(family);
            //TODO: Figure out how to set Due Date Automatically
            //invoice.setDueDate();
            invoice.setAmountDue(calculatedAmountDue);
            //Save Invoice
            return invoiceRepository.save(invoice);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error creating invoice", e);
        }
    }

    @Override
    public String updateInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
        return "Invoice Updated Successfully";
    }

    @Override
    public String deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
        return "Invoice Removed Successfully";
    }

    @Override
    public Invoice getInvoice(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> findInvoicesByDueDate(Date dueDate) {
        return invoiceRepository.findByDueDate(dueDate);
    }

    @Override
    public List<Invoice> findInvoicesByFamilyId(Long familyId) {
        return invoiceRepository.findByFamilyId(familyId);
    }
}
