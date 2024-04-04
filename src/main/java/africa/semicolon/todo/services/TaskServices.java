package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Task;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.request.UpdateTaskRequest;
import africa.semicolon.todo.dtos.response.TaskResponse;

import java.util.List;

public interface TaskServices {
    List<Task> getAllTask();
    String getTaskByTitle(String id);

//    TaskResponse createTask(TaskRequest task);

    // updateTask(TaskRequest task);

    TaskResponse updateTask(UpdateTaskRequest task);

    String deleteTask(CreateTaskRequest deleteTaskRequest);

    long getCountOfTask();

    String taskDone(Task task);

    void deleteTask(Task task);

    void deleteAll();

    Task findByTitle(String title);

    Task save(Task newTask);
}
