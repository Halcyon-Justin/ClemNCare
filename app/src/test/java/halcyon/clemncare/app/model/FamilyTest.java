package halcyon.clemncare.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FamilyTest {

    private Family family;

    @BeforeEach
    void setUp() {
        family = new Family();
    }

    @Test
    void testHasActiveChildrenWhenNoChildren() {
        // Arrange
        family.setChildren(null);

        // Act
        boolean result = family.hasActiveChildren();

        // Assert
        assertFalse(result, "Family with no children should not have active children");
    }

    @Test
    void testHasActiveChildrenWhenAllChildrenInactive() {
        // Arrange
        List<Child> inactiveChildren = Arrays.asList(createChild(false), createChild(false));
        family.setChildren(inactiveChildren);

        // Act
        boolean result = family.hasActiveChildren();

        // Assert
        assertFalse(result, "Family with all inactive children should not have active children");
    }

    @Test
    void testHasActiveChildrenWhenSomeChildrenActive() {
        // Arrange
        List<Child> mixedChildren = Arrays.asList(createChild(true), createChild(false));
        family.setChildren(mixedChildren);

        // Act
        boolean result = family.hasActiveChildren();

        // Assert
        assertTrue(result, "Family with at least one active child should have active children");
    }

    @Test
    void testGetActiveChildrenWhenNoChildren() {
        // Arrange
        family.setChildren(null);

        // Act
        List<Child> result = family.getActiveChildren();

        // Assert
        assertTrue(result.isEmpty(), "Family with no children should have no active children");
    }

    @Test
    void testGetActiveChildrenWhenAllChildrenInactive() {
        // Arrange
        List<Child> inactiveChildren = Arrays.asList(createChild(false), createChild(false));
        family.setChildren(inactiveChildren);

        // Act
        List<Child> result = family.getActiveChildren();

        // Assert
        assertTrue(result.isEmpty(), "Family with all inactive children should have no active children");
    }

    @Test
    void testGetActiveChildrenWhenSomeChildrenActive() {
        // Arrange
        List<Child> mixedChildren = Arrays.asList(createChild(true), createChild(false));
        family.setChildren(mixedChildren);

        // Act
        List<Child> result = family.getActiveChildren();

        // Assert
        assertEquals(1, result.size(), "Family with at least one active child should have one active child");
    }

    // Helper method to create a Child object with the specified active status
    private Child createChild(boolean isActive) {
        Child child = new Child();
        child.setActive(isActive);
        return child;
    }
}
