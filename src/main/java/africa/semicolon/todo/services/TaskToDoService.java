package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Task;
import africa.semicolon.todo.data.model.Todo;
import africa.semicolon.todo.dtos.request.CreateTaskRequest;
import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.response.TaskResponse;
import africa.semicolon.todo.exceptions.TitleAlreadyExistException;
import africa.semicolon.todo.exceptions.TodoDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.todo.utils.Mapper.map;

@Service
@AllArgsConstructor
public class TaskToDoService {
    private final TodoServices todoServices;
    private final TaskServices taskServices;

    public String addTask(String todoId, Task savedTask) {
        Todo todo = todoServices.findTodoById(todoId);

        List<Task> tasks = todo.getTasks();
        tasks.add(savedTask);

        todo.setTasks(tasks);
        return "Task Successfully Added";
    }

    public List<Task> getAllTask() {
        return taskServices.getAllTask();
    }

    public void deleteTask(Task task) {
        taskServices.deleteTask(task);
    }
    public String deleteTodo(CreateTodoRequest deleteTodo1) {
        Todo foundTodo = todoServices.findByTitle(deleteTodo1.getTitle());

        if (foundTodo != null){
            for (Task task: getAllTask()) {
                if (task.getTodoId().equals(foundTodo.getId())){
                    deleteTask(task);
                }
            }
            todoServices.delete(foundTodo);
            return "Todo Successfully Deleted";
        }
        throw new TodoDoesNotExistException("Todo not found");
    }
    public TaskResponse createTask(CreateTaskRequest task) {
        Task foundTask = taskServices.findByTitle(task.getTitle());
        if (foundTask == null){
            Task newTask = map(task);
            TaskResponse response = map(newTask);
            Task savedTask = taskServices.save(newTask);
            addTask(task.getTodoId(), savedTask);
            return response;
        }
        throw new TitleAlreadyExistException("Title already exist");
    }

}
