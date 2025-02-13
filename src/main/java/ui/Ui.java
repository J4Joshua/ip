package ui;

/**
 * Handles interactions with the user by providing formatted messages for display in the GUI.
 */
public class Ui {

    /**
     * Returns a welcome message to be displayed in the GUI when the program starts.
     *
     * @return The formatted welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Ekko\nWhat can I do for you?";
    }

    /**
     * Returns a horizontal line separator for structuring GUI messages.
     *
     * @return A horizontal separator line.
     */
    public String getLineSeparator() {
        return "____________________________________________________________";
    }

    /**
     * Returns a formatted message for displaying general information to the user.
     *
     * @param message The message content.
     * @return The formatted message.
     */
    public String formatMessage(String message) {
        return message;
    }

    /**
     * Returns a formatted error message.
     *
     * @param message The error message content.
     * @return The formatted error message prefixed with "Error: ".
     */
    public String formatErrorMessage(String message) {
        return "Error: " + message;
    }

    /**
     * Returns a formatted message for loading errors.
     *
     * @param message The error details.
     * @return The formatted error message prefixed with "Error loading tasks: ".
     */
    public String formatLoadingErrorMessage(String message) {
        return "Error loading tasks: " + message;
    }
}