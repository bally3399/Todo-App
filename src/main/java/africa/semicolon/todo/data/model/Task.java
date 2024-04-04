package africa.semicolon.todo.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Document("Tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private LocalDateTime timeCreated = LocalDateTime.now();
    private boolean status = false;
    private Level description;
    private LocalDateTime timeDone = LocalDateTime.now();
    private String todoId;
}
