package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.EkkoException;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkkoException {
        Task task = tasks.getTask(index);
        task.markDone();
        ui.showLine();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("[" + task.getStatusIcon() + "] " + task);
        ui.showLine();
    }
}