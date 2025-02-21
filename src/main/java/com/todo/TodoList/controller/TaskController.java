package com.todo.TodoList.controller;


import com.todo.TodoList.model.Task;
import com.todo.TodoList.model.User;
import com.todo.TodoList.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    TaskService taskService;


    @Operation(summary = "Cria uma nova tarefa", description = "Retorna a tarefa que foi criada")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
            content = @Content(schema = @Schema(implementation = Task.class),
                    examples = @ExampleObject(value = "{\"tittle\":\"Task1\"\"description\": \"Finalizar projeto\", \"status\": \"Pendente\", \"priority\": \"Alta\", \"user\": \"body de user\"}")))
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody
      @Parameter(description = "Objeto Task a ser criado", required = true,
                    content = @Content(schema = @Schema(implementation = Task.class)))
        Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Lista todas as tarefas", description = "Retorna uma lista contendo todas as tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        return taskService.listAllTasks();
    }

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Busca uma tarefa por ID", description = "Retorna uma tarefa com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada",
            content = @Content(schema = @Schema(implementation = Task.class),
                    examples = @ExampleObject(value = "{\"id\":\"1\"\"tittle\":\"Task1\"\"description\": \"Finalizar projeto\", \"status\": \"Pendente\", \"priority\": \"Alta\", \"user\": \"body de user\"}")))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(
            @Parameter(description = "ID da tarefa a ser buscada", example = "1")
            @PathVariable (value = "id") Long id) {
        return taskService.findTaskById(id);
    }

    @PutMapping("/updatetasks/{id}")
    @Operation(summary = "Atualiza uma tarefa", description = "Atualiza os dados de uma tarefa existente.")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = Task.class),
                    examples = @ExampleObject(value = "{\"tittle\":\"Task1\"\"descriprion\": \"Finalizar projeto\", \"status\": \"Concluida\", \"priority\": \"Alta\", \"user\": \"body de user\"}")))
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task) {
        return taskService.updateTaskById(task,id);
    }

    @DeleteMapping("/deletetask/{id}")
    @Operation(summary = "Exclui uma tarefa", description = "Remove uma tarefa do banco de dados com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public void deleteTaskById(
            @Parameter(description = "ID da tarefa a ser excluída", example = "1")
            @PathVariable long id) {
        taskService.deleteTask(id);
    }


}
