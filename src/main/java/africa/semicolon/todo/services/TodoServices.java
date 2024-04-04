package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Todo;
import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.request.UpdateTodoRequest;
import africa.semicolon.todo.dtos.response.TodoResponse;

public interface TodoServices {
    TodoResponse createToDo(CreateTodoRequest createTodoRequest);


    TodoResponse updateToDo(UpdateTodoRequest updateTodo);

    void deleteAll();

    Todo findTodoById(String todoId);

    Todo findByTitle(String title);

    void delete(Todo foundTodo);
}
