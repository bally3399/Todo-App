package africa.semicolon.todo.dtos.request;

import africa.semicolon.todo.data.model.Level;
import lombok.Data;

@Data
public class CreateTaskRequest {
    private String title;
    private Level description;
    private String todoId;
}
