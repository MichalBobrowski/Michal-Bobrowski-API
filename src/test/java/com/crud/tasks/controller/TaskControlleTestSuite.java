package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.TrelloFacade;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TaskControlleTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private TaskController taskController;
    @MockBean
    private TrelloClient trelloClient;
    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void getTasksTest() throws Exception{
        //given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test", "T"));

        when(taskController.getTasks()).thenReturn(taskDtoList);
        //when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Test")));
    }


    @Test
    public void getTaskTest() throws Exception{
        //given
        TaskDto taskDto = new TaskDto(1L, "Test", "T");

        when(taskController.getTask(1L)).thenReturn(taskDto);
        //when & then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1L").contentType(MediaType.APPLICATION_JSON))
                //.param("testId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test")));
    }
}
