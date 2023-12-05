package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import halcyon.clemncare.app.dto.ChildDTO;
import halcyon.clemncare.app.exception.ChildNotFoundException;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.service.implementation.ChildServiceImpl;

public class ChildServiceImplTest {

    @Mock
    private ChildRepository childRepository;

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private ChildServiceImpl childService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateChild() {
        // Arrange
        ChildDTO childDTO = new ChildDTO();
        childDTO.setFamilyId(1L);

        Family mockFamily = new Family();
        when(familyRepository.findById(childDTO.getFamilyId())).thenReturn(Optional.of(mockFamily));

        Child savedChild = new Child();
        when(childRepository.save(any(Child.class))).thenReturn(savedChild);

        // Act
        Child result = childService.createChild(childDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedChild, result);

        // Verify that familyRepository's findById method was called with the correct parameter
        verify(familyRepository).findById(childDTO.getFamilyId());

        // Verify that childRepository's save method was called with the correct parameter
        verify(childRepository).save(any(Child.class));
    }

    @Test
    void testUpdateChild() {
        // Arrange
        Long childId = 1L;
        ChildDTO childDTO = new ChildDTO();

        Child existingChild = new Child();
        when(childRepository.findById(childId)).thenReturn(Optional.of(existingChild));

        Child updatedChild = new Child();
        when(childRepository.save(any(Child.class))).thenReturn(updatedChild);

        // Act
        Child result = childService.updateChild(childId, childDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedChild, result);

        // Verify that childRepository's findById method was called with the correct parameter
        verify(childRepository).findById(childId);

        // Verify that childRepository's save method was called with the correct parameter
        verify(childRepository).save(any(Child.class));
    }

    @Test
    void testPartialUpdateChild() {
        // Arrange
        Long childId = 1L;
        ChildDTO childDTO = new ChildDTO();

        Child existingChild = new Child();
        when(childRepository.findById(childId)).thenReturn(Optional.of(existingChild));

        Child updatedChild = new Child();
        when(childRepository.save(any(Child.class))).thenReturn(updatedChild);

        // Act
        Child result = childService.partialUpdateChild(childId, childDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedChild, result);

        // Verify that childRepository's findById method was called with the correct parameter
        verify(childRepository).findById(childId);

        // Verify that childRepository's save method was called with the correct parameter
        verify(childRepository).save(any(Child.class));
    }

    @Test
    void testDeleteChild() {
        // Arrange
        Long childId = 1L;

        // Act
        childService.deleteChild(childId);

        // Verify that childRepository's deleteById method was called with the correct parameter
        verify(childRepository).deleteById(childId);
    }

    @Test
    void testGetChild() {
        // Arrange
        Long childId = 1L;
        Child expectedChild = new Child();
        when(childRepository.findById(childId)).thenReturn(Optional.of(expectedChild));

        // Act
        Child result = childService.getChild(childId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedChild, result);
    }

    @Test
    void testGetAllChildren() {
        // Arrange
        List<Child> children = List.of(new Child(), new Child());
        when(childRepository.findAll()).thenReturn(children);

        // Act
        List<Child> result = childService.getAllChildren();

        // Assert
        assertNotNull(result);
        assertEquals(children.size(), result.size());
    }

    @Test
    void testFindChildrenByAge() {
        // Arrange
        int ageToFind = 5;

        // Case 1: Children with the specified age exist
        Child child1 = createChildWithAge(ageToFind);
        Child child2 = createChildWithAge(ageToFind);
        List<Child> childrenWithAge = List.of(child1, child2);
        when(childRepository.findAll()).thenReturn(childrenWithAge);

        // Act
        List<Child> resultWithAge = childService.findChildrenByAge(ageToFind);

        // Assert
        assertNotNull(resultWithAge);
        assertEquals(childrenWithAge.size(), resultWithAge.size());

        // Case 2: No children with the specified age
        int ageNotPresent = 10;
        when(childRepository.findAll()).thenReturn(List.of());

        // Act and Assert
        ChildNotFoundException exception = assertThrows(ChildNotFoundException.class,
                () -> childService.findChildrenByAge(ageNotPresent));
        assertEquals("No children found for the specified age", exception.getMessage());
    }

    private Child createChildWithAge(int age) {
        Child child = new Child();
        child.setDateOfBirth(LocalDate.now().minusYears(age));
        return child;
    }

}
