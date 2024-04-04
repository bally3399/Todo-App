package africa.semicolon.todo.utils;

import africa.semicolon.todo.data.model.Task;
import africa.semicolon.todo.data.model.Todo;
import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.response.TodoResponse;
import africa.semicolon.todo.dtos.response.TaskResponse;

import java.time.LocalDateTime;

public class Mapper {
    public static Task map(CreateTaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setTodoId(taskRequest.getTodoId());
        task.setTimeCreated(LocalDateTime.now());
        task.setId(task.getId());
        return task;
    }
    public static TaskResponse map(Task task){
        TaskResponse response = new TaskResponse();
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setTimeCreated(task.getTimeCreated());
        response.setId(task.getId());
        response.setTodoId(task.getTodoId());
        response.setTimeDone(task.getTimeDone());
        return response;
    }

    public static Todo map(CreateTodoRequest todoRequest){
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());
        return  todo;
    }
    public static TodoResponse map(Todo todo){
        TodoResponse response = new TodoResponse();
        response.setTitle(todo.getTitle());
        response.setMessage("Todo Successfully Created");
        response.setId(todo.getId());
        return response;
    }

}
