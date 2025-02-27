package command;

import storage.Storage;
import task.TaskList;

/**
 * Terminates the chatbot.
 */
public class ExitCommand extends Command {

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}