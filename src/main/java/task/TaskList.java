package task;

import java.util.ArrayList;

import exception.EkkoException;

/**
 * Manages a list of tasks, allowing addition, deletion, and retrieval of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The index of the task to delete (0-based).
     * @return The deleted task.
     * @throws EkkoException If the index is out of bounds.
     */
    public Task delete(int index) throws EkkoException {
        if (index < 0 || index >= tasks.size()) {
            throw new EkkoException("Task number " + (index + 1) + " does not exist.");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     * @throws EkkoException If the index is out of bounds.
     */
    public Task getTask(int index) throws EkkoException {
        if (index < 0 || index >= tasks.size()) {
            throw new EkkoException("Task number " + (index + 1) + " does not exist.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The {@link ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
