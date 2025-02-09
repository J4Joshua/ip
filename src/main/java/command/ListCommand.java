package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkkoException {
        if (tasks.size() == 0) {
            throw new EkkoException("List is empty");
        }

        ui.showLine();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            ui.showMessage(" " + (i + 1) + ". [" + task.getCategory() + "][" + task.getStatusIcon() + "] " + task);
        }
        ui.showLine();
    }
}
