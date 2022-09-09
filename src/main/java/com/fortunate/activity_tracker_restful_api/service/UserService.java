package com.fortunate.activity_tracker_restful_api.service;


import com.fortunate.activity_tracker_restful_api.dto.TaskDTO;
import com.fortunate.activity_tracker_restful_api.dto.UserDTO;
import com.fortunate.activity_tracker_restful_api.model.Task;
import com.fortunate.activity_tracker_restful_api.model.User;

import java.util.List;

public interface UserService {
    User register(UserDTO userDTO);
    User getUserByEmail(String email);
    Task createTask(TaskDTO taskDTO, int user_id);
    Task updateTitleAndDescription(TaskDTO taskDTO, int id);
    boolean updateTaskStatus(String status, int id);
    List<Task> viewAllTasks();
    Task getTaskById(int id);
    boolean deleteById(int id);

    String moveForward(int id);

    String moveBackward(int id);

    User getUserById(int id);
    List<Task> showTaskByUser(int id);

    User userLogin(UserDTO userDTO);

    User loginUser(String email, String password);


    List<Task> findAllByUser_idAndStatus(int user_id , String status);
}
