package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException {
        Task task = tasks.getTask(index);
        task.markUndone();
        return "OK, I've marked this task as not done yet:\n"
                + "[" + task.getStatusIcon() + "] " + task;
    }
}