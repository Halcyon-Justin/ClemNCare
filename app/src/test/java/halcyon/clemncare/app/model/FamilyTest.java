package halcyon.clemncare.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class FamilyTest {

    @Test
    void testGetFamilyAddress() {
        Family family = new Family();
        HomeAddress address = new HomeAddress();
        family.setAddress(address);

        assertEquals(address, family.getAddress());
    }

    @Test
    void testGetFamilyChildren() {
        Family family = new Family();
        List<Child> children = Arrays.asList(new Child(), new Child());
        family.setChildren(children);

        assertEquals(children, family.getChildren());
    }

    @Test
    void testGetFamilyGuardians() {
        Family family = new Family();
        List<Guardian> guardians = Arrays.asList(new Guardian(), new Guardian());
        family.setGuardians(guardians);

        assertEquals(guardians, family.getGuardians());
    }

    @Test
    void testGetEmergencyContact() {
        Family family = new Family();
        Guardian emergencyContact = new Guardian();
        family.setEmergencyContact(emergencyContact);

        assertEquals(emergencyContact, family.getEmergencyContact());
    }
}

