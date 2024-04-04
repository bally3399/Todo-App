package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Task;
import africa.semicolon.todo.data.repositories.TaskRepository;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.request.UpdateTaskRequest;
import africa.semicolon.todo.dtos.response.TaskResponse;
import africa.semicolon.todo.exceptions.TaskDoesNotExitException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static africa.semicolon.todo.utils.Mapper.map;

@Service
@AllArgsConstructor
public class TaskServicesImpl implements TaskServices{
    private final TaskRepository taskRepository;
    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public String getTaskByTitle(String title) {
        taskRepository.findByTitle(title);
        return title;
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest task) {
        Task updateTask = taskRepository.findByTitle(task.getTitle());
        if (task.getTitle()!= null && task.getDescription() != null && task.getTodoId() != null){
            updateTask.setTitle(task.getNewTitle());
            updateTask.setTodoId(task.getTodoId());
            updateTask.setDescription(task.getNewDescription());
        }
        Task savedTask = taskRepository.save(updateTask);
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTitle(savedTask.getTitle());
        taskResponse.setTodoId(savedTask.getTodoId());
        taskResponse.setDescription(savedTask.getDescription());
        return taskResponse;
    }

    @Override
    public String deleteTask(CreateTaskRequest deleteTaskRequest) {
       Task foundTask = taskRepository.findByTitle(deleteTaskRequest.getTitle());

        if (foundTask != null){
            taskRepository.delete(foundTask);
            return "Task Successfully Deleted";
        }
        throw new TaskDoesNotExitException("Task not found");

    }
    @Override
    public long getCountOfTask() {
        return taskRepository.count();
    }
    @Override
    public String taskDone(Task task){
        task.setStatus(true);
        task.setTimeDone(LocalDateTime.now());
        return "successful";
    }

    @Override
    public void deleteTask(Task task) {
        Task foundTask = taskRepository.findByTitle(task.getTitle());
        taskRepository.deleteByTitle(foundTask.getTitle());
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @Override
    public Task findByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    @Override
    public Task save(Task newTask) {
        return taskRepository.save(newTask);
    }
}
