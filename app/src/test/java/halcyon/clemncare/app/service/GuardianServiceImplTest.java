package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.service.implementation.GuardianServiceImpl;

public class GuardianServiceImplTest {

    @Mock
    private GuardianRepository guardianRepository;

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private GuardianServiceImpl guardianService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGuardian() {
        // Arrange
        GuardianDTO guardianDTO = new GuardianDTO();
        guardianDTO.setFamilyId(1L);

        Family mockFamily = new Family();
        when(familyRepository.findById(guardianDTO.getFamilyId())).thenReturn(Optional.of(mockFamily));

        Guardian savedGuardian = new Guardian();
        when(guardianRepository.save(any(Guardian.class))).thenReturn(savedGuardian);

        // Act
        Guardian result = guardianService.createGuardian(guardianDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedGuardian, result);

        // Verify that familyRepository's findById method was called with the correct parameter
        verify(familyRepository).findById(guardianDTO.getFamilyId());

        // Verify that guardianRepository's save method was called with the correct parameter
        verify(guardianRepository).save(any(Guardian.class));
    }

    @Test
    void testUpdateGuardian() {
        // Arrange
        Long guardianId = 1L;
        GuardianDTO guardianDTO = new GuardianDTO();

        Guardian existingGuardian = new Guardian();
        when(guardianRepository.findById(guardianId)).thenReturn(Optional.of(existingGuardian));

        Guardian updatedGuardian = new Guardian();
        when(guardianRepository.save(any(Guardian.class))).thenReturn(updatedGuardian);

        // Act
        Guardian result = guardianService.updateGuardian(guardianId, guardianDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedGuardian, result);

        // Verify that guardianRepository's findById method was called with the correct parameter
        verify(guardianRepository).findById(guardianId);

        // Verify that guardianRepository's save method was called with the correct parameter
        verify(guardianRepository).save(any(Guardian.class));
    }

    @Test
    void testPartialUpdateGuardian() {
        // Arrange
        Long guardianId = 1L;
        GuardianDTO guardianDTO = new GuardianDTO();

        Guardian existingGuardian = new Guardian();
        when(guardianRepository.findById(guardianId)).thenReturn(Optional.of(existingGuardian));

        Guardian updatedGuardian = new Guardian();
        when(guardianRepository.save(any(Guardian.class))).thenReturn(updatedGuardian);

        // Act
        Guardian result = guardianService.partialUpdateGuardian(guardianId, guardianDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedGuardian, result);

        // Verify that guardianRepository's findById method was called with the correct parameter
        verify(guardianRepository).findById(guardianId);

        // Verify that guardianRepository's save method was called with the correct parameter
        verify(guardianRepository).save(any(Guardian.class));
    }

    @Test
    void testDeleteGuardian() {
        // Arrange
        Long guardianId = 1L;

        // Act
        guardianService.deleteGuardian(guardianId);

        // Verify that guardianRepository's deleteById method was called with the correct parameter
        verify(guardianRepository).deleteById(guardianId);
    }

    @Test
    void testGetGuardian() {
        // Arrange
        Long guardianId = 1L;
        Guardian expectedGuardian = new Guardian();
        when(guardianRepository.findById(guardianId)).thenReturn(Optional.of(expectedGuardian));

        // Act
        Optional<Guardian> result = guardianService.getGuardian(guardianId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedGuardian, result.get());
    }

    @Test
    void testGetAllGuardians() {
        // Arrange
        List<Guardian> guardians = List.of(new Guardian(), new Guardian());
        when(guardianRepository.findAll()).thenReturn(guardians);

        // Act
        List<Guardian> result = guardianService.getAllGuardians();

        // Assert
        assertNotNull(result);
        assertEquals(guardians.size(), result.size());
    }
}
