package command;

import storage.Storage;
import task.Task;
import task.TaskList;

public class SortCommand extends Command {
    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) {
        tasks.sortTasks();
        return "Tasks have been sorted!\n" + tasks.toString();
    }
}
