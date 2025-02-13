package command;

import exception.EkkoException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Handles adding a new task.
 */
public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Storage storage) throws EkkoException {
        assert input != null : "Input cannot be null";

        String[] splitInput = input.split(" ", 2);
        String commandWord = splitInput[0];

        if (splitInput.length < 2) {
            throw new EkkoException("The description of a task cannot be empty.");
        }

        Task newTask;
        switch (commandWord) {
        case "todo":
            newTask = new ToDo(splitInput[1]);
            break;

        case "deadline":
            String[] deadlineParts = splitInput[1].split(" /by ", 2);
            if (deadlineParts.length < 2) {
                throw new EkkoException("Deadline format should be: deadline task /by d/M/yyyy HHmm");
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;

        case "event":
            String[] eventParts = splitInput[1].split(" /from | /to ", 3);
            if (eventParts.length < 3) {
                throw new EkkoException("Event format should be: event task /from d/M/yyyy HHmm /to d/M/yyyy HHmm");
            }
            newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
            break;

        default:
            throw new EkkoException("Invalid task type.");
        }

        tasks.add(newTask);
        storage.save(tasks);
        return "Got it! I've added this task:\n  " + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}