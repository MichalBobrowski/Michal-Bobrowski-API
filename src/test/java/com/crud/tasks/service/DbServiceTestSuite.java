package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTaskTest(){
        //given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test", "con"));
        when(repository.findAll()).thenReturn(taskList);
        //when
        List<Task> result = service.getAllTasks();
        //then
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void saveTask(){
        //given
        Task task = new Task(1L, "Test", "con");
        when(repository.save(task)).thenReturn(task);

        //when
        Task result = service.saveTask(task);

        //then
        Assert.assertEquals("Test", result.getTitle());
    }
}
