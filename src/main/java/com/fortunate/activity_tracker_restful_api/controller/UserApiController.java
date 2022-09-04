package com.fortunate.activity_tracker_restful_api.controller;

import com.fortunate.activity_tracker_restful_api.dto.TaskDTO;
import com.fortunate.activity_tracker_restful_api.dto.UserDTO;
import com.fortunate.activity_tracker_restful_api.model.Task;
import com.fortunate.activity_tracker_restful_api.model.User;
import com.fortunate.activity_tracker_restful_api.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    private final UserServiceImpl userService;

    @Autowired
    public UserApiController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value="/register")
    public User registration(@RequestBody UserDTO userDTO){
        User registeredUser = userService.register(userDTO);
        System.out.println("User registered successfully");
        return registeredUser;
    }

    @GetMapping(value="/login")
    public User userLogin(@RequestBody UserDTO userDTO){
        User loggedInUser = userService.userLogin(userDTO);
        System.out.println("User logged in successfully");
        return loggedInUser;
    }

    @PostMapping(value = "/createTask")
    public Task createTask(@RequestBody TaskDTO taskDTO) {
        return userService.createTask(taskDTO);
    }

    @GetMapping(value="/viewAllTasks")
    public List<Task> getAllTasks() {
        return userService.viewAllTasks();
    }

    @GetMapping(value = "/getTask/{id}")
    public Task getTask(@PathVariable(value = "id") int id) {
        return userService.getTaskById(id);
    }

    @PostMapping(value = "/updateTask/{id}")
    public Task editTask(@RequestBody TaskDTO taskDTO, @PathVariable(value = "id") Integer id) {
        return userService.updateTitleAndDescription(taskDTO, id);
    }
}
