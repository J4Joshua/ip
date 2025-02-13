import command.Command;
import exception.EkkoException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The main chatbot application that processes user input and interacts with the GUI.
 */
public class Ekko {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Ekko with the specified file path for storage.
     *
     * @param filePath The path to the data file for storing tasks.
     */
    public Ekko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EkkoException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a welcome message to be displayed in the GUI.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user's input message.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeAndGetResponse(tasks, storage);
        } catch (EkkoException e) {
            return ui.formatErrorMessage(e.getMessage());
        }
    }
}