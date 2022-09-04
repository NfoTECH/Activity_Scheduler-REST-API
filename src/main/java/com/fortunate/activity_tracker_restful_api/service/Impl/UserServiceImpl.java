package com.fortunate.activity_tracker_restful_api.service.Impl;


import com.fortunate.activity_tracker_restful_api.dto.TaskDTO;
import com.fortunate.activity_tracker_restful_api.dto.UserDTO;
import com.fortunate.activity_tracker_restful_api.exception.TaskNotFoundException;
import com.fortunate.activity_tracker_restful_api.exception.UserNotFoundException;
import com.fortunate.activity_tracker_restful_api.model.Task;
import com.fortunate.activity_tracker_restful_api.model.User;
import com.fortunate.activity_tracker_restful_api.repository.TaskRepository;
import com.fortunate.activity_tracker_restful_api.repository.UserRepository;
import com.fortunate.activity_tracker_restful_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public User register(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User userLogin(UserDTO userDTO) {
        User user = getUserByEmail(userDTO.getEmail());
        return (user.getPassword().equals(userDTO.getPassword())) ? user : null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("This user was not found"));
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("This user was not found"));
    }


    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task task =  new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus("PENDING");
        return taskRepository.save(task);
    }

    @Override
    public List<Task> showTaskByUser(int id){
        return  taskRepository.listOfTasksByUserId(id);
    }
    @Override
    public Task updateTitleAndDescription(TaskDTO taskDTO, int id) {
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public boolean updateTaskStatus(String status, int id) {
        return taskRepository.updateTaskByIdAndStatus(status, id);
    }

    @Override
    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("This task was not found"));
    }

    @Override
    public boolean deleteById(int id) {
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public String moveForward(int id) {
        Task task = taskRepository.findById(id).get();
        String status = task.getStatus();
        if (status.equals("PENDING")) {
            task.setStatus("IN_PROGRESS");
        } else if (status.equals("IN_PROGRESS")) {
            task.setStatus("DONE");
            task.setCompletedAt(LocalDateTime.now());
        } else {
            return "Task is already completed";
        }
        taskRepository.save(task);
        return "Task status updated";
    }


    @Override
    public String moveBackward(int id) {
        Task task = taskRepository.findById(id).get();
        String status = task.getStatus();
        if (status.equals("IN_PROGRESS")) {
            task.setStatus("PENDING");
        } else {
            return "Task is already pending";
        }
        taskRepository.save(task);
        return "Task status updated";
    }
}

//    @Override
//    public List<Task> findAllByUser_idAndStatus(int user_id , String status){
//        return taskRepository.findAllByUser_idAndStatus(user_id , status);
//    }
