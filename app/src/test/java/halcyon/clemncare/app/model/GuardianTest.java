package halcyon.clemncare.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GuardianTest {

    @Test
    void testGetGuardianName() {
        Guardian guardian = new Guardian();
        guardian.setFirstName("Alice");
        guardian.setLastName("Smith");

        assertEquals("Alice Smith", guardian.getName());
    }
}

