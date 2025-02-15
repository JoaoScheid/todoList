package com.todo.TodoList.service;

import com.todo.TodoList.model.Task;
import com.todo.TodoList.model.User;
import com.todo.TodoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<User> findUserById(Long id){
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> updateUserById(User user, Long id) {
        return userRepository.findById(id)
                .map(userToUpdate -> {
                    userToUpdate.setNome(user.getNome());
                    userToUpdate.setEmail(user.getEmail());
                    userToUpdate.setSenha(user.getSenha());
                    User updatedUser = userRepository.save(userToUpdate);
                    return ResponseEntity.ok().body(updatedUser);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
