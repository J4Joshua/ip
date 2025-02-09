package task;

/**
 * Represents a generic task with a description, status, and category.
 * Acts as the parent class for specific task types like {@code ToDo}, {@code Deadline}, and {@code Event}.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Category category;

    /**
     * Constructs a new {@code Task} with the given description and category.
     *
     * @param description The description of the task.
     * @param category    The category of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, Category category) {
        this.description = description;
        this.isDone = false;
        this.category = category;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the category of the task.
     *
     * @return The {@link Category} of the task.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task, which is its description.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
}
