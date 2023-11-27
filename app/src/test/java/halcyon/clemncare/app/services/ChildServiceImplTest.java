package halcyon.clemncare.app.services;

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

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.services.implementation.ChildServiceImpl;

@ExtendWith(MockitoExtension.class)
class ChildServiceImplTest {

    @Mock
    private ChildRepository childRepository;

    @InjectMocks
    private ChildServiceImpl childService;

    @Test
    void testCreateChild() {
        Child child = new Child();

        when(childRepository.save(any())).thenReturn(child);

        String result = childService.createChild(child);

        assertEquals("Child Saved Successfully", result);
        verify(childRepository, times(1)).save(child);
    }

    @Test
    void testUpdateChild() {
        Child child = new Child();

        when(childRepository.save(any())).thenReturn(child);

        String result = childService.updateChild(child);

        assertEquals("Child Updated Successfully", result);
        verify(childRepository, times(1)).save(child);
    }

    @Test
    void testDeleteChild() {
        Long childId = 1L;

        String result = childService.deleteChild(childId);

        assertEquals("Child Removed Successfully", result);
        verify(childRepository, times(1)).deleteById(childId);
    }

    @Test
    void testGetChild() {
        Long childId = 1L;
        Child child = new Child();

        when(childRepository.findById(childId)).thenReturn(Optional.of(child));

        Child result = childService.getChild(childId);

        assertEquals(child, result);
        verify(childRepository, times(1)).findById(childId);
    }

    @Test
    void testGetAllChildren() {
        List<Child> children = Arrays.asList(new Child(), new Child());

        when(childRepository.findAll()).thenReturn(children);

        List<Child> result = childService.getAllChildren();

        assertEquals(children, result);
        verify(childRepository, times(1)).findAll();
    }
}

