package com.todo.TodoList.repository;

import com.todo.TodoList.model.Task;
import com.todo.TodoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
