package halcyon.clemncare.app.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.exception.InvoiceNotFoundException;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.InvoiceRepository;
import halcyon.clemncare.app.service.InvoiceCalculationService;
import halcyon.clemncare.app.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    InvoiceCalculationService invoiceCalculationService;

    @Override
    @Transactional
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Optional<Family> family = familyRepository.findById(invoiceDTO.getFamily().getId());
        if(family.isPresent()) {
            Invoice invoice = new Invoice();
            invoice.setFamily(family.get());
            // TODO: Figure Out Automated Due Dates
            // invoice.setDueDate(invoiceDTO.getDueDate());
            Long amountDue = invoiceCalculationService.calculateAmountDue(family.get().getId());
            invoice.setAmountDue(amountDue);
            return invoiceRepository.save(invoice);
        } else {
            throw new FamilyNotFoundException("Family with ID: " + invoiceDTO.getFamily().getId() + " does not exist");
        }
    }

    @Override
    public Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            BeanUtils.copyProperties(invoiceDTO, existingInvoice);
            return invoiceRepository.save(existingInvoice);
        } else {
            throw new InvoiceNotFoundException("Invoice with ID: " + id + " does not exist");
        }
    }

    @Override
    public Invoice partialUpdateInvoice(Long id, InvoiceDTO invoiceDTO) {
        Invoice existinInvoice = invoiceRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(invoiceDTO, existinInvoice);
        return invoiceRepository.save(existinInvoice);
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
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
