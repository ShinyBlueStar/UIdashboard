package com.ceadar.uidashboard.schedule;

import com.ceadar.uidashboard.datamodel.entity.Status;
import com.ceadar.uidashboard.datamodel.entity.TaskEntity;
import com.ceadar.uidashboard.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SchedulerUtilTest {

    @InjectMocks
    private Scheduler schedulerUtil;

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateTaskStatuses() {
        // Arrange
        TaskEntity task1 = new TaskEntity();
        task1.setStatus(Status.ONPROGRESS);
        task1.setEndedAt(null);

        TaskEntity task2 = new TaskEntity();
        task2.setStatus(Status.FINISHED);
        task2.setEndedAt(null);

        when(taskService.findAll()).thenReturn(List.of(task1, task2));

        // Act
        schedulerUtil.updateTaskStatuses();

        // Assert
        verify(taskService, times(1)).onUpdate(task1);
        verify(taskService, never()).onUpdate(task2); // task2 should not be updated
        assertEquals(LocalDateTime.now().getSecond(), task1.getEndedAt().getSecond(), 1); // Check that endedAt is set
    }

    @Test
    void testAddNewTask() {
        // Arrange
        when(taskService.count()).thenReturn(1L); // Simulate that there is already one task

        // Act
        schedulerUtil.addNewTask();

        // Assert
        ArgumentCaptor<TaskEntity> taskCaptor = ArgumentCaptor.forClass(TaskEntity.class);
        verify(taskService, times(1)).onSave(taskCaptor.capture());

        TaskEntity savedTask = taskCaptor.getValue();
        assertEquals("Task 2", savedTask.getName()); // Verify the task name
        assertTrue(Arrays.asList(Status.values()).contains(savedTask.getStatus()));
    }

    @Test
    void testGenerateRandomLocalDateTime() {
        // Arrange
        int daysInPast = 5;

        // Act
        LocalDateTime randomDateTime = SchedulerUtil.generateRandomDateAndTime.apply(5);

        // Assert
        assertEquals(true, randomDateTime.isBefore(LocalDateTime.now().plusDays(1))); // Check it's in the past
    }

//    @Test
//    generateRandomStatus
}