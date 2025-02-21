package com.todo.TodoList.service;


import com.todo.TodoList.model.Task;
import com.todo.TodoList.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author joao_p_scheid
 *Está é uma classe service, responsavel por intermediar a comunicação entre os controllers
 *(que recebem as requisições HTTP) e os repositórios(que acessam o banco de dados). Ela contém
 * a lógica de negócio da aplicação.
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    /**
     * Cria uma nova tarefa e a salva no banco de dados.
     *
     * @param task Objeto {@link Task} contendo os dados da nova tarefa.
     * @return A tarefa criada e persistida.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Responsável por listar todas as tarefas que foram persistidas atráves do save
     * @return Lista de tarefas cadastradas
     */
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Responsável por listar apenas uma tarefa do banco de dados, atráves do Id dela que sera
     * passado via pathvariable no controller
     * @param id Identificador único da tarefa
     * @return A tarefa correspondente ao ID fornecido.
     */
    public Task findTaskById(Long id){
        return taskRepository.findById(id).get();
    }

    /**
     * Atualiza uma tarefa existente com base no ID informado.
     *
     * @param task Objeto {@link Task} contendo os novos dados da tarefa.
     * @param id   Identificador único da tarefa a ser atualizada.
     * @return A tarefa atualizada e persistida no banco de dados.
     */
    public Task updateTaskById(Task task, Long id) {
        task.setId(id);
        return taskRepository.save(task);
    }


    /**
     * Exclui uma tarefa do banco de dados com base no ID informado.
     *
     * @param id Identificador único da tarefa a ser excluída.
     */
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
