package africa.semicolon.todo.dtos.response;

import africa.semicolon.todo.data.model.Level;
import africa.semicolon.todo.data.model.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private String id;
    private String title;
    private LocalDateTime timeCreated = LocalDateTime.now();
    private boolean status = false;
    private Level description;
    private LocalDateTime timeDone = LocalDateTime.now();
    private String todoId;

}