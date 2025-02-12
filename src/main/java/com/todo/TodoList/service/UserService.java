package com.todo.TodoList.service;

import com.todo.TodoList.model.Task;
import com.todo.TodoList.model.User;
import com.todo.TodoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
