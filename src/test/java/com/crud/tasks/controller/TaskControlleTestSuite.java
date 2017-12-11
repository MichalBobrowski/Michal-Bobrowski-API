package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.TrelloFacade;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControlleTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TrelloClient trelloClient;
    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void getTasksTest() throws Exception {
        //given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test", "T"));

        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.anyList())).thenReturn(taskDtoList);
        //when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Test")));
    }


    @Test
    public void getTaskTest() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L, "Test", "T");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any())).thenReturn(taskDto);
        when(service.getTaskById(1L)).thenReturn(Optional.of(new Task(1L, "Test", "T")));
        //when & then
        mockMvc.perform(get("/v1/task/getTask?taskId=1")//.param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                //.param("testId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test")));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //when & then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTaskTest() throws Exception {
        //given
        TaskDto updatedTaskDto = new TaskDto(1L, "UpdateTest", "UT");
        Task updatedTask = new Task(1L, "UpdateTest", "UT");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any())).thenReturn(updatedTaskDto);
        when(service.saveTask(updatedTask)).thenReturn(updatedTask);
        //when & then
        mockMvc.perform(put("/v1/task/updateTask").requestAttr("taskDto", updatedTask)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("UpdateTest")));
    }
    @Test
    public void createTaskTest() throws Exception{
        //given
        Task createdTask = new Task(1L, "CreatedTest", "CT");
        TaskDto createdTaskDto = new TaskDto(1L, "CreatedTest", "CT");

        when(taskMapper.mapToTask(ArgumentMatchers.any())).thenReturn(createdTask);
        when(service.saveTask(ArgumentMatchers.any())).thenReturn(createdTask);
        //when & then
        mockMvc.perform(post("/v1/task/createTask").requestAttr("taskDto", createdTaskDto)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}