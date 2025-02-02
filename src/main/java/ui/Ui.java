package ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ekko");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError(String message) {
        System.out.println("Error loading tasks: " + message);
    }
}