package task;

import java.util.ArrayList;
import exception.EkkoException;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        this.tasks = tasks;
    }

    public void add(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
    }

    public Task delete(int index) throws EkkoException {
        assert index >= 0 : "Index cannot be negative";
        if (index >= tasks.size()) {
            throw new EkkoException("Task number " + (index + 1) + " does not exist.");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws EkkoException {
        assert index >= 0 : "Index should be non-negative";
        if (index >= tasks.size()) {
            throw new EkkoException("Task number " + (index + 1) + " does not exist.");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}