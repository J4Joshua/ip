package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException {
        return "Task removed: " + tasks.delete(index);
    }
}
