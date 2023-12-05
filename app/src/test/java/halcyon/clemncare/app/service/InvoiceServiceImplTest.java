package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.exception.InvoiceNotFoundException;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.InvoiceRepository;
import halcyon.clemncare.app.service.implementation.InvoiceServiceImpl;

public class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private InvoiceCalculationService invoiceCalculationService;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateInvoice() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        Family family = new Family();
        invoiceDTO.setFamily(family);

        Family mockFamily = new Family();
        when(familyRepository.findById(invoiceDTO.getFamily())).thenReturn(Optional.of(mockFamily));

        Invoice mockInvoice = new Invoice();
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(mockInvoice);

        when(invoiceCalculationService.calculateAmountDue(mockFamily.getId())).thenReturn(100L);

        // Act
        Invoice result = invoiceService.createInvoice(invoiceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(mockInvoice, result);

        // Verify that invoiceRepository's save method was called with the correct parameter
        verify(invoiceRepository).save(any(Invoice.class));

        // Verify that invoiceCalculationService's calculateAmountDue method was called with the correct parameter
        verify(invoiceCalculationService).calculateAmountDue(mockFamily.getId());
    }

    @Test
    void testUpdateInvoice() {
        // Arrange
        Long invoiceId = 1L;
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setAmountDue(150L);

        Invoice existingInvoice = new Invoice();
        existingInvoice.setId(invoiceId);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(existingInvoice));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(existingInvoice);

        // Act
        Invoice result = invoiceService.updateInvoice(invoiceId, invoiceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(existingInvoice, result);
        assertEquals(invoiceDTO.getAmountDue(), result.getAmountDue());

        // Verify that invoiceRepository's findById method was called with the correct parameter
        verify(invoiceRepository).findById(invoiceId);

        // Verify that invoiceRepository's save method was called with the correct parameter
        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void testUpdateInvoiceNotFound() {
        // Arrange
        Long invoiceId = 1L;
        InvoiceDTO invoiceDTO = new InvoiceDTO();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvoiceNotFoundException.class, () -> {
            invoiceService.updateInvoice(invoiceId, invoiceDTO);
        });

        // Verify that invoiceRepository's findById method was called with the correct parameter
        verify(invoiceRepository).findById(invoiceId);

        // Verify that invoiceRepository's save method was not called
        verify(invoiceRepository, never()).save(any(Invoice.class));
    }

    @Test
    void testPartialUpdateInvoice() {
        // Arrange
        Long invoiceId = 1L;
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setAmountDue(150L);

        Invoice existingInvoice = new Invoice();
        existingInvoice.setId(invoiceId);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(existingInvoice));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(existingInvoice);

        // Act
        Invoice result = invoiceService.partialUpdateInvoice(invoiceId, invoiceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(existingInvoice, result);
        assertEquals(invoiceDTO.getAmountDue(), result.getAmountDue());

        // Verify that invoiceRepository's findById method was called with the correct parameter
        verify(invoiceRepository).findById(invoiceId);

        // Verify that invoiceRepository's save method was called with the correct parameter
        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    void testDeleteInvoice() {
        // Arrange
        Long invoiceId = 1L;

        // Act
        invoiceService.deleteInvoice(invoiceId);

        // Verify that invoiceRepository's deleteById method was called with the correct parameter
        verify(invoiceRepository).deleteById(invoiceId);
    }

    @Test
    void testGetInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice expectedInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // Act
        Optional<Invoice> result = invoiceService.getInvoice(invoiceId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedInvoice, result.get());

        // Verify that invoiceRepository's findById method was called with the correct parameter
        verify(invoiceRepository).findById(invoiceId);
    }

    @Test
    void testGetAllInvoices() {
        // Arrange
        List<Invoice> expectedInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        // Act
        List<Invoice> result = invoiceService.getAllInvoices();

        // Assert
        assertNotNull(result);
        assertEquals(expectedInvoices, result);

        // Verify that invoiceRepository's findAll method was called
        verify(invoiceRepository).findAll();
    }

    @Test
    void testFindInvoicesByDueDate() {
        // Arrange
        Date dueDate = new Date();
        List<Invoice> expectedInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findByDueDate(dueDate)).thenReturn(expectedInvoices);

        // Act
        List<Invoice> result = invoiceService.findInvoicesByDueDate(dueDate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedInvoices, result);

        // Verify that invoiceRepository's findByDueDate method was called with the correct parameter
        verify(invoiceRepository).findByDueDate(dueDate);
    }

    @Test
    void testFindInvoicesByFamilyId() {
        // Arrange
        Long familyId = 1L;
        List<Invoice> expectedInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findByFamilyId(familyId)).thenReturn(expectedInvoices);

        // Act
        List<Invoice> result = invoiceService.findInvoicesByFamilyId(familyId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedInvoices, result);

        // Verify that invoiceRepository's findByFamilyId method was called with the correct parameter
        verify(invoiceRepository).findByFamilyId(familyId);
    }

}