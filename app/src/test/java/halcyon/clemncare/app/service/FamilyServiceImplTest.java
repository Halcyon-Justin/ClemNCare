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

import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.service.implementation.FamilyServiceImpl;

@ExtendWith(MockitoExtension.class)
class FamilyServiceImplTest {

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private FamilyServiceImpl familyService;

    @Test
    void testCreateFamily() {
        Family family = new Family();

        when(familyRepository.save(any())).thenReturn(family);

        String result = familyService.createFamily(family);

        assertEquals("Family Saved Successfully", result);
        verify(familyRepository, times(1)).save(family);
    }

    @Test
    void testUpdateFamily() {
        Family family = new Family();

        when(familyRepository.save(any())).thenReturn(family);

        String result = familyService.updateFamily(family);

        assertEquals("Family Updated Successfully", result);
        verify(familyRepository, times(1)).save(family);
    }

    @Test
    void testDeleteFamily() {
        Long familyId = 1L;

        String result = familyService.deleteFamily(familyId);

        assertEquals("Family Deleted Successfully", result);
        verify(familyRepository, times(1)).deleteById(familyId);
    }

    @Test
    void testGetFamily() {
        Long familyId = 1L;
        Family family = new Family();

        when(familyRepository.findById(familyId)).thenReturn(Optional.of(family));

        Family result = familyService.getFamily(familyId);

        assertEquals(family, result);
        verify(familyRepository, times(1)).findById(familyId);
    }

    @Test
    void testGetAllFamilies() {
        List<Family> families = Arrays.asList(new Family(), new Family());

        when(familyRepository.findAll()).thenReturn(families);

        List<Family> result = familyService.getAllFamilies();

        assertEquals(families, result);
        verify(familyRepository, times(1)).findAll();
    }
}

