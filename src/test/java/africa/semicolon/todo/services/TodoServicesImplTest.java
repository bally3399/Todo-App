package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Level;
import africa.semicolon.todo.data.repositories.TodoRepository;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.request.UpdateTodoRequest;
import africa.semicolon.todo.dtos.response.TaskResponse;
import africa.semicolon.todo.dtos.response.TodoResponse;
import africa.semicolon.todo.exceptions.TitleAlreadyExistException;
import africa.semicolon.todo.services.TaskServices;
import africa.semicolon.todo.services.TaskToDoService;
import africa.semicolon.todo.services.TodoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TodoServicesImplTest {

    @Autowired
    TodoServices todoServices;
    @Autowired
    private TaskToDoService taskToDoService;
    @Autowired
    private TaskServices taskServices;
    @Autowired
    private TodoRepository todoRepository;
    @BeforeEach
    public void setUp(){
        todoRepository.deleteAll();
        taskServices.deleteAll();
    }

    @Test
    public void createTodoTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        var todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");
    }

    @Test
    public void createTodoWithSameTitleTest_ThrowTitleAlreadyExist(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        todoServices.createToDo(createTodoRequest);
        assertThrows(TitleAlreadyExistException.class,() -> todoServices.createToDo(createTodoRequest));
    }
    @Test
    public void createTodo_updateTodoTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");
        todoServices.createToDo(createTodoRequest);

        UpdateTodoRequest updateTodo = new UpdateTodoRequest();
        updateTodo.setTitle("Title");
        updateTodo.setNewTitle("new title");
        TodoResponse update = todoServices.updateToDo(updateTodo);
        assertEquals("new title", update.getTitle());
    }

    @Test
    public void createTodo_DeleteTodoTest(){
        CreateTodoRequest createTodo = new CreateTodoRequest();
        createTodo.setTitle("Title");
        TodoResponse todo = todoServices.createToDo(createTodo);


        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.URGENT);
        createTaskRequest.setTodoId(todo.getId());
        TaskResponse taskResponse = taskToDoService.createTask(createTaskRequest);
        assertEquals("title", taskServices.getTaskByTitle("title"));

        String response = taskToDoService.deleteTodo(createTodo);
        assertEquals(response, "Todo Successfully Deleted");

        assertEquals(taskResponse.getTitle(), "title");
    }


}