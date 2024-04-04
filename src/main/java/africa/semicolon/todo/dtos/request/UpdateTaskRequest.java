package africa.semicolon.todo.dtos.request;

import africa.semicolon.todo.data.model.Level;
import lombok.Data;

@Data
public class UpdateTaskRequest {
    private String title;
    private String newTitle;
    private Level description;
    private Level newDescription;
    private String todoId;

}
