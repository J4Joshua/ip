package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkkoException {
        Task task = tasks.getTask(index);
        task.markUndone();
        ui.showLine();
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage("[" + task.getStatusIcon() + "] " + task);
        ui.showLine();
    }
}
