package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Level;
import africa.semicolon.todo.data.repositories.TaskRepository;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.request.UpdateTaskRequest;
import africa.semicolon.todo.dtos.response.TaskResponse;
import africa.semicolon.todo.dtos.response.TodoResponse;
import africa.semicolon.todo.exceptions.TitleAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
public class TaskServicesImplTest {
    @Autowired
    private TaskServices taskServices;
    @Autowired
    private TaskToDoService taskToDoService;
    @Autowired
    private TodoServices todoServices;

    @Autowired
    public TaskRepository taskRepository;

    @BeforeEach
    public void setUp(){
        taskRepository.deleteAll();
        todoServices.deleteAll();
    }


    @Test
    public void createTaskTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        System.out.println(todo.getId());

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.IMPORTANT);
        createTaskRequest.setTodoId(todo.getId());
        TaskResponse taskResponse = taskToDoService.createTask(createTaskRequest);


        assertEquals(taskResponse.getTitle(), "title");
        assertEquals(taskResponse.getTodoId(), todo.getId());


    }

    @Test
    public void createTwoTaskTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.LESS_IMPORTANT);
        createTaskRequest.setTodoId(todo.getId());
        TaskResponse taskResponse = taskToDoService.createTask(createTaskRequest);

        CreateTaskRequest createTaskRequest1 = new CreateTaskRequest();
        createTaskRequest1.setTitle("title1");
        createTaskRequest1.setDescription(Level.LESS_IMPORTANT);
        createTaskRequest1.setTodoId(todo.getId());
        taskToDoService.createTask(createTaskRequest1);
        assertEquals(taskResponse.getTitle(), "title");
        assertEquals(taskResponse.getTodoId(), todo.getId());

    }

   @Test
    public void createTaskAndGetTaskByIdTest() {
       CreateTodoRequest createTodoRequest = new CreateTodoRequest();
       createTodoRequest.setTitle("Title");

       TodoResponse todo = todoServices.createToDo(createTodoRequest);
       assertEquals(todo.getMessage(), "Todo Successfully Created");

       CreateTaskRequest createTaskRequest = new CreateTaskRequest();
       createTaskRequest.setTitle("title");
       createTaskRequest.setDescription(Level.URGENT);
       createTaskRequest.setTodoId(todo.getId());
       taskToDoService.createTask(createTaskRequest);
       assertEquals("title", taskServices.getTaskByTitle("title"));

    }

    @Test
    public void updateTaskTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.IMPORTANT);
        createTaskRequest.setTodoId(todo.getId());
        taskToDoService.createTask(createTaskRequest);

        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setTitle("title");
        updateTaskRequest.setNewTitle("Updated Title");
        updateTaskRequest.setDescription(Level.IMPORTANT);
        updateTaskRequest.setNewDescription(Level.LESS_IMPORTANT);
        updateTaskRequest.setTodoId(todo.getId());
        TaskResponse updatedTask = taskServices.updateTask(updateTaskRequest);
        assertEquals("Updated Title", updatedTask.getTitle());
    }
    @Test
    public void createTask_deleteTaskTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.LESS_URGENT);
        createTaskRequest.setTodoId(todo.getId());
        taskToDoService.createTask(createTaskRequest);
        taskServices.deleteTask(createTaskRequest);
        assertEquals(0, taskServices.getCountOfTask());
    }

    @Test
    public void createTwoTaskDeleteOneTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.IMPORTANT);
        createTaskRequest.setTodoId(todo.getId());
        taskToDoService.createTask(createTaskRequest);

        CreateTaskRequest createTaskRequest1 = new CreateTaskRequest();
        createTaskRequest1.setTitle("title1");
        createTaskRequest1.setDescription(Level.LESS_IMPORTANT);
        createTaskRequest1.setTodoId(todo.getId());
        taskToDoService.createTask(createTaskRequest1);

        taskServices.deleteTask(createTaskRequest);
        assertEquals(1, taskServices.getCountOfTask());
    }
    @Test
    public void createTaskAndGetAllTaskTest(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.LESS_URGENT);
        createTaskRequest.setTodoId(todo.getId());


        taskToDoService.createTask(createTaskRequest);

        CreateTaskRequest createTaskRequest1 = new CreateTaskRequest();
        createTaskRequest1.setTitle("title1");
        createTaskRequest1.setDescription(Level.IMPORTANT);
        createTaskRequest1.setTodoId(todo.getId());
        taskToDoService.createTask(createTaskRequest1);
        var task = taskServices.getAllTask();
        assertEquals(2, task.size());
    }
    @Test
    public void createTaskWithSameTitle_ThrowTitleAlreadyExistException(){
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTitle("Title");

        TodoResponse todo = todoServices.createToDo(createTodoRequest);
        assertEquals(todo.getMessage(), "Todo Successfully Created");

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("title");
        createTaskRequest.setDescription(Level.LESS_URGENT);
        createTaskRequest.setTodoId(todo.getId());


        taskToDoService.createTask(createTaskRequest);
        assertThrows(TitleAlreadyExistException.class,()-> taskToDoService.createTask(createTaskRequest));
    }
}