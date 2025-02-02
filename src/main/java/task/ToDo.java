package task;

/**
 * Represents an ToDo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description, Category.TODO);
    }
}