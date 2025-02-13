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
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException {
        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            response.append((i + 1))
                    .append(". [").append(task.getCategory())
                    .append("][").append(task.getStatusIcon())
                    .append("] ").append(task).append("\n");
        }
        return response.toString();
    }
}
