package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.EkkoException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The index of the task to delete (zero-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkkoException {
        Task removedTask = tasks.delete(index);

        ui.showLine();
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("[" + removedTask.getCategory() + "][" + removedTask.getStatusIcon() + "] " + removedTask);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();

        // Save updated task list after deletion
        storage.save(tasks.getTasks());
    }
}