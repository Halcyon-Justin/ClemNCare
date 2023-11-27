package halcyon.clemncare.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import halcyon.clemncare.app.model.Guardian;

class GuardianTest {

    @Test
    void testGetGuardianName() {
        Guardian guardian = new Guardian();
        guardian.setFirstName("Alice");
        guardian.setLastName("Smith");

        assertEquals("Alice Smith", guardian.getName());
    }
}

