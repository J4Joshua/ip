package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import exception.EkkoException;

/**
 * Terminates the chatbot.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMessage("Bye. Hope to see you again soon!");
        ui.showLine();
    }

    /**
     * Indicates that this command should exit the program.
     *
     * @return true, as ExitCommand ends the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}