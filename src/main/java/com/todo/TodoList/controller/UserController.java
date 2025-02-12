package com.todo.TodoList.controller;

import com.todo.TodoList.model.Task;
import com.todo.TodoList.model.User;
import com.todo.TodoList.service.TaskService;
import com.todo.TodoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createTask(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }
}
