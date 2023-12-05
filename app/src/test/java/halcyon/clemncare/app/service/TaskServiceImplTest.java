package halcyon.clemncare.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import halcyon.clemncare.app.exception.GuardianNotFoundException;
import halcyon.clemncare.app.model.Task;
import halcyon.clemncare.app.repositories.TaskRepository;
import halcyon.clemncare.app.service.implementation.TaskServiceImpl;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTask() {
        // Arrange
        Task task = new Task();
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task result = taskService.createTask(new Task());

        // Assert
        assertEquals(task, result);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        Task updatedTask = new Task();
        updatedTask.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        // Act
        Task result = taskService.updateTask(taskId, updatedTask);

        // Assert
        assertEquals(updatedTask, result);
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testUpdateTask_NotFound() {
        // Arrange
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(GuardianNotFoundException.class, () -> taskService.updateTask(taskId, new Task()));
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    public void testGetTask() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        Task result = taskService.getTask(taskId);

        // Assert
        assertEquals(task, result);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    public void testGetTask_NotFound() {
        // Arrange
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(GuardianNotFoundException.class, () -> taskService.getTask(taskId));
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    public void testGetTasks() {
        // Arrange
        List<Task> tasks = Collections.singletonList(new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<Task> result = taskService.getTasks();

        // Assert
        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }
}
