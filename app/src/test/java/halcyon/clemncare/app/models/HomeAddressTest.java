package halcyon.clemncare.app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import halcyon.clemncare.app.enums.StateCode;
import halcyon.clemncare.app.model.HomeAddress;

class HomeAddressTest {

    @Test
    void testGetAddressDetails() {
        HomeAddress address = new HomeAddress();
        address.setStreetAddress("123 Main St");
        address.setCity("Cityville");
        address.setState(StateCode.CA);
        address.setZipCode("12345");

        assertEquals("123 Main St, Cityville, CA 12345", address.getAddressDetails());
    }
}
