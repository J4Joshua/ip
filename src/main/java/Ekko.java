import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public class TaskList {
        private ArrayList<Task> tasks;

        public TaskList() {
            this.tasks = new ArrayList<>();
        }

        public TaskList(ArrayList<Task> tasks) {
            this.tasks = tasks;
        }

        public void add(Task task) {
            tasks.add(task);
        }

        public Task delete(int index) throws EkkoException {
            if (index < 0 || index >= tasks.size()) {
                throw new EkkoException("Task number " + (index + 1) + " does not exist.");
            }
            return tasks.remove(index);
        }

        public Task getTask(int index) throws EkkoException {
            if (index < 0 || index >= tasks.size()) {
                throw new EkkoException("Task number " + (index + 1) + " does not exist.");
            }
            return tasks.get(index);
        }

        public int size() {
            return tasks.size();
        }

        public ArrayList<Task> getTasks() {
            return tasks;
        }
    }

    public class Storage {
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
            try {
                File file = new File(filePath);
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("File already exists: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while checking/creating the file.");
                e.printStackTrace();
            }
        }

        public ArrayList<Task> load() throws EkkoException {
            ArrayList<Task> tasks = new ArrayList<>();
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3)
                        continue;

                    String type = parts[0];
                    boolean isDone = parts[1].equals("X");
                    String description = parts[2];
                    Task task = null;

                    if (type.equals("T")) {
                        task = new ToDo(description);
                    } else if (type.equals("D") && parts.length == 4) {
                        task = new Deadline(description, parts[3]);
                    } else if (type.equals("E") && parts.length == 5) {
                        task = new Event(description, parts[3], parts[4]);
                    }

                    if (task != null) {
                        if (isDone) {
                            task.markDone();
                        }
                        tasks.add(task);
                    }
                }
                System.out.println("Tasks successfully loaded from file.");
            } catch (IOException e) {
                throw new EkkoException("Error loading tasks: " + e.getMessage());
            }
            return tasks;
        }

        public void save(ArrayList<Task> tasks) throws EkkoException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : tasks) {
                    String type = task.getCategory().toString();
                    String status = task.isDone() ? "X" : " ";
                    String description = task.getDescription();
                    if (task instanceof Deadline) {
                        writer.write(type + " | " + status + " | " + description + " | " + ((Deadline) task).getDate());
                    } else if (task instanceof Event) {
                        writer.write(type + " | " + status + " | " + description + " | " +
                                ((Event) task).getFrom() + " | " + ((Event) task).getTo());
                    } else {
                        writer.write(type + " | " + status + " | " + description);
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new EkkoException("Error saving tasks: " + e.getMessage());
            }
        }
    }

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

    // Custom exception for Ekko
    public static class EkkoException extends Exception {
        public EkkoException(String message) {
            super(message);
        }
    }

    public enum Category {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");

        private final String symbol;

        Category(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected Category category;

        public Task(String description, Category category) {
            this.description = description;
            this.isDone = false;
            this.category = category;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public Category getCategory() {
            return this.category;
        }

        public void markDone() {
            this.isDone = true;
        }

        public void markUndone() {
            this.isDone = false;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return this.description;
        }

        public boolean isDone() {
            return isDone;
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description, Category.TODO);
        }
    }

    public static class Deadline extends Task {
        protected String date;

        public Deadline(String description, String date) {
            super(description, Category.DEADLINE);
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        @Override
        public String toString() {
            return this.description + " (by: " + date + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description, Category.EVENT);
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        @Override
        public String toString() {
            return this.description + " (from: " + from + " to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        new Ekko("data/ekko.txt").run();
    }
}