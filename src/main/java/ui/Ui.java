package ui;

import java.util.Scanner;

/**
 * Handles interactions with the user, including displaying messages,
 * reading user commands, and showing errors.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the {@code Ui} with a new {@link Scanner} for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when the program starts.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ekko");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next line of input from the user and trims any leading or trailing whitespace.
     *
     * @return The trimmed user command as a {@link String}.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line separator to visually separate sections of output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a custom message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed, prefixed with "Error: ".
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays an error message related to loading tasks.
     *
     * @param message The error message to be displayed, prefixed with "Error loading tasks: ".
     */
    public void showLoadingError(String message) {
        System.out.println("Error loading tasks: " + message);
    }
}
