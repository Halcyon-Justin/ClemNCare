package halcyon.clemncare.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import halcyon.clemncare.app.model.ReportCard;

class ReportCardTest {

    @Test
    void testGetReportCardDetails() {
        ReportCard reportCard = new ReportCard();
        reportCard.setHasNapped(true);
        reportCard.setNotes("Good behavior");
        reportCard.setSendTo(List.of("parent1@example.com", "parent2@example.com"));

        assertEquals("Report Card Details: Has Napped: true, Notes: Good behavior, Send To: [parent1@example.com, parent2@example.com]", reportCard.getReportCardDetails());
    }
}

