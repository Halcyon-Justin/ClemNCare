package halcyon.clemncare.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ChildTest {

    @Test
    void testGetName() {
        Child child = new Child();
        child.setFirstName("John");
        child.setLastName("Doe");

        assertEquals("John Doe", child.getName());
    }

    @Test
    void testGetAge() {
        Child child = new Child();
        child.setDateOfBirth(LocalDate.of(2000, 1, 1));

        // Assuming the current date is 2023-01-01
        assertEquals(23, child.getAge());
    }
}

