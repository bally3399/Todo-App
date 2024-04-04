package africa.semicolon.todo.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoResponse {
    private String message;
    private String title;
    private String id;
}
