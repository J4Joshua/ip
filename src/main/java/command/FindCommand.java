package command;

import exception.EkkoException;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Searches for tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException {
        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                response.append((i + 1))
                        .append(". [").append(task.getCategory())
                        .append("][").append(task.getStatusIcon())
                        .append("] ").append(task).append("\n");
                found = true;
            }
        }

        if (!found) {
            return "No matching tasks found.";
        }
        return response.toString();
    }
}