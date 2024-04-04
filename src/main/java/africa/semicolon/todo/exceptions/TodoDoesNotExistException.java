package africa.semicolon.todo.exceptions;

public class TodoDoesNotExistException extends TodoAppExceptions {
    public TodoDoesNotExistException(String message) {
        super(message);
    }
}
