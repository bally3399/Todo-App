package africa.semicolon.data.repositories;

import africa.semicolon.todo.data.model.Task;
import africa.semicolon.todo.data.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    @BeforeEach
    public void setUp(){
        taskRepository.deleteAll();
    }

    @Test
    public void saveTaskTest(){
        Task task = new Task();
        taskRepository.save(task);
        assertEquals(1, taskRepository.count());
    }
    @Test
    public void saveTwoTaskTest(){
        Task task = new Task();
        taskRepository.save(task);
        Task task1 = new Task();
        taskRepository.save(task1);
        assertEquals(2,taskRepository.count());
    }
    @Test
    public void saveTaskAndDeleteById(){
        Task task = new Task();
        taskRepository.save(task);
        taskRepository.delete(task);
        assertEquals(0, taskRepository.count());
    }
    @Test
    public void saveTwoTaskAndDeleteOneTest(){
        Task task = new Task();
        taskRepository.save(task);
        Task task1 = new Task();
        taskRepository.save(task1);
        assertEquals(2,taskRepository.count());

        taskRepository.delete(task);
        assertEquals(1, taskRepository.count());
    }

}