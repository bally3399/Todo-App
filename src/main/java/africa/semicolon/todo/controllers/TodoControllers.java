package africa.semicolon.todo.controllers;

import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.request.UpdateTodoRequest;
import africa.semicolon.todo.dtos.response.ApiResponse;
import africa.semicolon.todo.dtos.response.TodoResponse;
import africa.semicolon.todo.exceptions.TodoAppExceptions;
import africa.semicolon.todo.services.TaskToDoService;
import africa.semicolon.todo.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/Todo")
public class TodoControllers {
    @Autowired
    private TodoServices todoServices;
    @Autowired
    private TaskToDoService taskToDoService;
    @PostMapping("/create")
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoRequest todoRequest){
        try{
            TodoResponse result = todoServices.createToDo(todoRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest){
        try{
            TodoResponse result = todoServices.updateToDo(updateTodoRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTodo(@RequestBody CreateTodoRequest todoRequest){
        try{
            String result = taskToDoService.deleteTodo(todoRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

}
