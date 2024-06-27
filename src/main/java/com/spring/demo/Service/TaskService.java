package com.spring.demo.Service;

import com.spring.demo.Model.Commenter;
import com.spring.demo.Model.Task;
import com.spring.demo.Model.User;
import com.spring.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(){
        Task task = new  Task(0, "title", "description", "owner",  "start_date", "end_date",  "status", "priority", null,  null);
        return taskRepository.save(task);
    }

}
