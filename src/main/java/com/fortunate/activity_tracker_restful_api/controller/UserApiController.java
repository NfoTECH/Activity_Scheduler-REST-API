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

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserDTO userDTO) {
        return userService.userLogin(userDTO);
    }


}
