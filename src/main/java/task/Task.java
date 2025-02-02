package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Category category;

    public Task(String description, Category category) {
        this.description = description;
        this.isDone = false;
        this.category = category;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Category getCategory() {
        return this.category;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public boolean isDone() {
        return isDone;
    }
}
