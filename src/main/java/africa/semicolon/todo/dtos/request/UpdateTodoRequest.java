package africa.semicolon.todo.dtos.request;

import lombok.Data;

@Data
public class UpdateTodoRequest {
    private String title;
    private String newTitle;
}
