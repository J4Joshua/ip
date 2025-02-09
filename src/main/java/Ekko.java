import command.Command;
import exception.EkkoException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The main chatbot application that handles user interaction,
 * task management, and storage operations.
 */
public class Ekko {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an {@code Ekko} chatbot with the specified file path for storage.
     *
     * @param filePath The path to the data file for storing tasks.
     */
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

    /**
     * Starts the chatbot, displaying the welcome message and handling user input
     * until the exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (EkkoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Ekko("data/ekko.txt").run();
    }
}
