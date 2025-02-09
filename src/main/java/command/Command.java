package command;

import exception.EkkoException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws EkkoException If execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EkkoException;

    /**
     * Determines if the command signals the program to exit.
     *
     * @return True if this command exits the program, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
