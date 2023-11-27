package halcyon.clemncare.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals; // Added import
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.services.implementation.HomeAddressServiceImpl;

@ExtendWith(MockitoExtension.class)
class HomeAddressServiceImplTest {

    @Mock
    private HomeAddressRepository homeAddressRepository;

    @InjectMocks
    private HomeAddressServiceImpl homeAddressService;

    @Test
    void testCreateAddress() {
        HomeAddress address = new HomeAddress();

        when(homeAddressRepository.save(any())).thenReturn(address);

        String result = homeAddressService.createAddress(address);

        assertEquals("Address Saved Successfully", result);
        verify(homeAddressRepository, times(1)).save(address);
    }

    @Test
    void testUpdateAddress() {
        HomeAddress address = new HomeAddress();

        when(homeAddressRepository.save(any())).thenReturn(address);

        String result = homeAddressService.updateAddress(address);

        assertEquals("Address Updated Successfully", result);
        verify(homeAddressRepository, times(1)).save(address);
    }

    @Test
    void testDeleteAddress() {
        Long addressId = 1L;

        String result = homeAddressService.deleteAddress(addressId);

        assertEquals("Address Deleted Successfully", result);
        verify(homeAddressRepository, times(1)).deleteById(addressId);
    }

    @Test
    void testGetAddress() {
        Long addressId = 1L;
        HomeAddress address = new HomeAddress();

        when(homeAddressRepository.getById(addressId)).thenReturn(address);

        HomeAddress result = homeAddressService.getAddress(addressId);

        assertEquals(address, result);
        verify(homeAddressRepository, times(1)).getById(addressId);
    }
}
