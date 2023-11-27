package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.service.implementation.GuardianServiceImpl;

@ExtendWith(MockitoExtension.class)
class GuardianServiceImplTest {

    @Mock
    private GuardianRepository guardianRepository;

    @InjectMocks
    private GuardianServiceImpl guardianService;

    @Test
    void testCreateGuardian() {
        Guardian guardian = new Guardian();

        when(guardianRepository.save(any())).thenReturn(guardian);

        String result = guardianService.createGuardian(guardian);

        assertEquals("Guardian Saved Successfully", result);
        verify(guardianRepository, times(1)).save(guardian);
    }

    @Test
    void testUpdateGuardian() {
        Guardian guardian = new Guardian();

        when(guardianRepository.save(any())).thenReturn(guardian);

        String result = guardianService.updateGuardian(guardian);

        assertEquals("Guardian Updated Successfully", result);
        verify(guardianRepository, times(1)).save(guardian);
    }

    @Test
    void testDeleteGuardian() {
        Long guardianId = 1L;

        String result = guardianService.deleteGuardian(guardianId);

        assertEquals("Guardian Deleted Successfully", result);
        verify(guardianRepository, times(1)).deleteById(guardianId);
    }

    @Test
    void testGetGuardian() {
        Long guardianId = 1L;
        Guardian guardian = new Guardian();

        when(guardianRepository.findById(guardianId)).thenReturn(Optional.of(guardian));

        Guardian result = guardianService.getGuardian(guardianId);

        assertEquals(guardian, result);
        verify(guardianRepository, times(1)).findById(guardianId);
    }

    @Test
    void testGetAllGuardians() {
        List<Guardian> guardians = Arrays.asList(new Guardian(), new Guardian());

        when(guardianRepository.findAll()).thenReturn(guardians);

        List<Guardian> result = guardianService.getAllGuardians();

        assertEquals(guardians, result);
        verify(guardianRepository, times(1)).findAll();
    }
}

