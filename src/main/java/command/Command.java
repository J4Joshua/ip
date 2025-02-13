package command;

import exception.EkkoException;
import storage.Storage;
import task.TaskList;

/**
 * Represents an executable command in the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command and returns the response as a string.
     *
     * @param tasks   The task list.
     * @param storage The storage instance.
     * @return The response message after execution.
     * @throws EkkoException If an error occurs during execution.
     */
    public abstract String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException;
}