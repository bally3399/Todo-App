package africa.semicolon.todo.controllers;

import africa.semicolon.todo.data.model.Task;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.request.UpdateTaskRequest;
import africa.semicolon.todo.dtos.response.ApiResponse;
import africa.semicolon.todo.dtos.response.TaskResponse;
import africa.semicolon.todo.exceptions.TodoAppExceptions;
import africa.semicolon.todo.services.TaskServices;
import africa.semicolon.todo.services.TaskToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/Task")
@AllArgsConstructor
public class TaskControllers {

    private final TaskServices taskServices;
    private final TaskToDoService taskToDoService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest){
        try{
            TaskResponse result = taskToDoService.createTask(createTaskRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest){
        try{
            TaskResponse result = taskServices.updateTask(updateTaskRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestBody CreateTaskRequest deleteTask){
        try{
            String result = taskServices.deleteTask(deleteTask);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<?> getAllTask(){
        try{
            List<Task> result = taskServices.getAllTask();
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/getTitle")
    public ResponseEntity<?> getTaskByTitle(@RequestParam String title){
        try{
            String result = taskServices.getTaskByTitle(title);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }catch (TodoAppExceptions e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }

}
