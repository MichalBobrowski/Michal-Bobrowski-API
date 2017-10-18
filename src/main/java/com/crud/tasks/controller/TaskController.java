package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(Long taskId){
        return taskMapper.mapToTaskDto(service.getTaskById(taskId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(String taskId){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto((long) 1 , "Edited test title", "Edited_test_content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(TaskDto taskDto){
        service.saveTask(taskMapper.mapToTask(taskDto));

    }
}
