package africa.semicolon.todo.data.repositories;

import africa.semicolon.todo.data.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    Task findByTitle(String title);
    void deleteByTitle(String string);



}
