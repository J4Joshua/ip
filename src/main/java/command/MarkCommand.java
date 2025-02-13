package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException {
        Task task = tasks.getTask(index);
        task.markDone();
        return "Nice! I've marked this task as done:\n"
                + "[" + task.getStatusIcon() + "] " + task;
    }
}