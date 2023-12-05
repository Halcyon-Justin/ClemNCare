package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import halcyon.clemncare.app.dto.HomeAddressDTO;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.service.implementation.HomeAddressServiceImpl;

public class HomeAddressServiceImplTest {

    @Mock
    private HomeAddressRepository homeAddressRepository;

    @InjectMocks
    private HomeAddressServiceImpl homeAddressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAddress() {
        // Arrange
        HomeAddressDTO addressDTO = new HomeAddressDTO();
        HomeAddress savedAddress = new HomeAddress();
        when(homeAddressRepository.save(any(HomeAddress.class))).thenReturn(savedAddress);

        // Act
        HomeAddress result = homeAddressService.createAddress(addressDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedAddress, result);

        // Verify that homeAddressRepository's save method was called with the correct parameter
        verify(homeAddressRepository).save(any(HomeAddress.class));
    }

    @Test
    void testUpdateAddress() {
        // Arrange
        Long addressId = 1L;
        HomeAddressDTO addressDTO = new HomeAddressDTO();

        HomeAddress existingAddress = new HomeAddress();
        when(homeAddressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));

        HomeAddress updatedAddress = new HomeAddress();
        when(homeAddressRepository.save(any(HomeAddress.class))).thenReturn(updatedAddress);

        // Act
        HomeAddress result = homeAddressService.updateAddress(addressId, addressDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedAddress, result);

        // Verify that homeAddressRepository's findById method was called with the correct parameter
        verify(homeAddressRepository).findById(addressId);

        // Verify that homeAddressRepository's save method was called with the correct parameter
        verify(homeAddressRepository).save(any(HomeAddress.class));
    }

    @Test
    void testPartialUpdateAddress() {
        // Arrange
        Long addressId = 1L;
        HomeAddressDTO addressDTO = new HomeAddressDTO();

        HomeAddress existingAddress = new HomeAddress();
        when(homeAddressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));

        HomeAddress updatedAddress = new HomeAddress();
        when(homeAddressRepository.save(any(HomeAddress.class))).thenReturn(updatedAddress);

        // Act
        HomeAddress result = homeAddressService.partialUpdateAddress(addressId, addressDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedAddress, result);

        // Verify that homeAddressRepository's findById method was called with the correct parameter
        verify(homeAddressRepository).findById(addressId);

        // Verify that homeAddressRepository's save method was called with the correct parameter
        verify(homeAddressRepository).save(any(HomeAddress.class));
    }

    @Test
    void testDeleteAddress() {
        // Arrange
        Long addressId = 1L;

        // Act
        homeAddressService.deleteAddress(addressId);

        // Verify that homeAddressRepository's deleteById method was called with the correct parameter
        verify(homeAddressRepository).deleteById(addressId);
    }

    @Test
    void testGetAddress() {
        // Arrange
        Long addressId = 1L;
        HomeAddress expectedAddress = new HomeAddress();
        when(homeAddressRepository.findById(addressId)).thenReturn(Optional.of(expectedAddress));

        // Act
        Optional<HomeAddress> result = homeAddressService.getAddress(addressId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedAddress, result.get());
    }
}
