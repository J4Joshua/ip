import ui.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import exception.EkkoException;
import storage.Storage;

/**
 * Main app entry point.
 */
public class Ekko {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Ekko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EkkoException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean exit = false;
        while (!exit) {
            try {
                String input = ui.readCommand();
                if (input.equals("bye")) {
                    ui.showMessage("____________________________________________________________");
                    ui.showMessage("Bye. Hope to see you again soon!");
                    ui.showMessage("____________________________________________________________");
                    exit = true;
                    continue;
                }
                handleInput(input);
                storage.save(tasks.getTasks());
            } catch (EkkoException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }

    public void handleInput(String input) throws EkkoException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            if (inputArr[0].equals("list")) {
                if (tasks.size() == 0) {
                    throw new EkkoException("List is empty");
                }
                ui.showLine();
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.getTask(i);
                    ui.showMessage(" " + (i + 1) + ". " + "[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "] " + task);
                }
                ui.showLine();
                return;
            } else {
                if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event") ||
                        input.startsWith("mark") || input.startsWith("unmark") || input.equals("bye")) {
                    throw new EkkoException("Cannot be blank after command");
                }
                throw new EkkoException("Invalid command! Available commands: todo, deadline, event, list, mark, unmark, delete, bye");
            }
        }

        String description = inputArr[1];

        if (input.matches("mark \\d+")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.getTask(index);
            task.markDone();
            ui.showLine();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("[" + task.getStatusIcon() + "] " + task);
            ui.showLine();
        } else if (input.matches("unmark \\d+")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.getTask(index);
            task.markUndone();
            ui.showLine();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage("[" + task.getStatusIcon() + "] " + task);
            ui.showLine();
        } else if (input.startsWith("todo")) {
            Task task = new ToDo(description);
            tasks.add(task);
            ui.showLine();
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "] " + task);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } else if (input.startsWith("deadline")) {
            String[] parts = description.split("/by", 2);
            if (parts.length < 2) {
                throw new EkkoException("Invalid deadline format");
            }
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.add(task);
            ui.showLine();
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "] " + task);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } else if (input.startsWith("event")) {
            String[] parts = description.split("/from", 2);
            if (parts.length < 2) {
                throw new EkkoException("Invalid event format");
            }
            String[] timeParts = parts[1].split("/to", 2);
            if (timeParts.length < 2) {
                throw new EkkoException("Invalid event format");
            }
            Task task = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            tasks.add(task);
            ui.showLine();
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "] " + task);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } else if (input.startsWith("delete")) {
            if (!input.matches("delete \\d+")) {
                throw new EkkoException("Invalid delete format");
            }
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = tasks.delete(index);
            ui.showLine();
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("[" + removedTask.getCategory() + "][" + removedTask.getStatusIcon() + "] " + removedTask);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } else {
            throw new EkkoException("Invalid command");
        }
    }

    public static void main(String[] args) {
        new Ekko("data/ekko.txt").run();
    }
}