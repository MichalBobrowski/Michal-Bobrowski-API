package com.crud.tasks.trello.client;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest(){
        //given
        TaskDto taskDto = new TaskDto(1L, "Testname", "testcont");
        //when
        Task result = taskMapper.mapToTask(taskDto);
        //then

        Assert.assertEquals(Long.valueOf(1L), Long.valueOf(result.getId()));
    }

    @Test
    public void mapToTaskDtoTest(){
        //given
         Task task = new Task(1L, "Testname", "testcont");
        //when
        TaskDto result = taskMapper.mapToTaskDto(task);
        //then

        Assert.assertEquals(Long.valueOf(1L), Long.valueOf(result.getId()));
    }

    @Test
    public void mapToTaskDtoList(){
        //given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Testname", "testcont");
        taskList.add(task);

        //when
        List<TaskDto> result =  taskMapper.mapToTaskDtoList(taskList);

        //then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals("Testname", result.get(0).getTitle());
    }

}
