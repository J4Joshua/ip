package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Searches for tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkkoException {
        ui.showLine();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                ui.showMessage(" " + (i + 1) + ". [" + task.getCategory() + "][" + task.getStatusIcon() + "] " + task);
                found = true;
            }
        }
        if (!found) {
            ui.showMessage("No matching tasks found.");
        }
        ui.showLine();
    }
}
