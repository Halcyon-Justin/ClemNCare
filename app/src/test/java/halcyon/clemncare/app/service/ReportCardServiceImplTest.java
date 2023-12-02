package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import halcyon.clemncare.app.dto.ReportCardRequest;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.ReportCardRepository;
import halcyon.clemncare.app.service.implementation.ReportCardServiceImpl;

@ExtendWith(MockitoExtension.class)
class ReportCardServiceImplTest {

    @Mock
    private ChildRepository childRepository;

    @Mock
    private ReportCardRepository reportCardRepository;

    @InjectMocks
    private ReportCardServiceImpl reportCardService;

    @Test
    void testCreateReportCard() {
        ReportCardRequest reportCardRequest = new ReportCardRequest();
        reportCardRequest.setHasNapped(true);
        reportCardRequest.setNotes("Good behavior");

        Long childId = 1L;

        Child child = new Child();
        child.setId(childId);

        Family family = new Family();
        family.setGuardians(Collections.singletonList(new Guardian()));

        child.setFamily(family);

        when(childRepository.getById(childId)).thenReturn(child);
        when(reportCardRepository.save(any())).thenReturn(new ReportCard());

        ReportCard result = reportCardService.createReportCard(reportCardRequest, childId);

        assertNotNull(result);
        verify(childRepository, times(1)).getById(childId);
        verify(reportCardRepository, times(1)).save(any());
    }

    @Test
    void testUpdateReportCard() {
        ReportCard reportCard = new ReportCard();

        when(reportCardRepository.save(any())).thenReturn(reportCard);

        String result = reportCardService.updateReportCard(reportCard);

        assertEquals("Report Card Updated Successfully", result);
        verify(reportCardRepository, times(1)).save(reportCard);
    }

    @Test
    void testDeleteReportCard() {
        Long reportCardId = 1L;

        String result = reportCardService.deleteReportCard(reportCardId);

        assertEquals("Report Card Deleted Successfully", result);
        verify(reportCardRepository, times(1)).deleteById(reportCardId);
    }

    @Test
    void testGetReportCard() {
        Long reportCardId = 1L;
        ReportCard reportCard = new ReportCard();

        when(reportCardRepository.findById(reportCardId)).thenReturn(Optional.of(reportCard));

        ReportCard result = reportCardService.getReportCard(reportCardId);

        assertEquals(reportCard, result);
        verify(reportCardRepository, times(1)).findById(reportCardId);
    }
}

