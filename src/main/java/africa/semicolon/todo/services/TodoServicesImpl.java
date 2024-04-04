package africa.semicolon.todo.services;

import africa.semicolon.todo.data.model.Todo;
import africa.semicolon.todo.data.repositories.TodoRepository;
import africa.semicolon.todo.dtos.request.CreateTodoRequest;
import africa.semicolon.todo.dtos.request.UpdateTodoRequest;
import africa.semicolon.todo.dtos.response.TodoResponse;
import africa.semicolon.todo.exceptions.TitleAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.todo.utils.Mapper.map;

@Service
public class TodoServicesImpl implements TodoServices{
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoResponse createToDo(CreateTodoRequest request) {
        Todo foundTodo = todoRepository.findByTitle(request.getTitle());
        if(foundTodo == null){
            Todo newTodo = map(request);
            Todo savedTodo = todoRepository.save(newTodo);
            return map(savedTodo);
        }
        throw new TitleAlreadyExistException("Title already exist");
    }

    @Override
    public TodoResponse updateToDo(UpdateTodoRequest updateTodo) {
        Todo foundTodo = todoRepository.findByTitle(updateTodo.getTitle());
        foundTodo.setTitle(updateTodo.getNewTitle());
        Todo savedTodo = todoRepository.save(foundTodo);
        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setTitle(savedTodo.getTitle());
        return todoResponse;

    }

    @Override
    public void deleteAll() {
        todoRepository.deleteAll();
    }


    @Override
    public Todo findTodoById(String todoId) {
        return todoRepository.findTodoById(todoId);
    }

    @Override
    public Todo findByTitle(String title) {
        return todoRepository.findByTitle(title);
    }

    @Override
    public void delete(Todo foundTodo) {
        todoRepository.delete(foundTodo);
    }
}
