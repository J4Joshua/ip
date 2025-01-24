import java.util.ArrayList;
import java.util.Scanner;

public class Ekko {
    public static ArrayList<Task> list = new ArrayList<>();

    public static class EkkoException extends Exception {
        public EkkoException(String message) {
            super(message);
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected Character category = 'N';

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public Character getCategory() {
            return this.category;
        }

        public void markDone() {
            this.isDone = true;
        }

        public void markUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return this.description;
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
            this.category = 'T';
        }
    }

    public static class Deadline extends Task {
        protected String date;
        public Deadline(String description, String date) {
            super(description);
            this.category = 'D';
            this.date = date;
        }

        @Override
        public String toString() {
            return this.description + "(by:" + date + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;
        public Event(String description, String from, String to) {
            super(description);
            this.category = 'E';
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return this.description + "(from:" + from + "to:" + to + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "____________________________________________________________\n" +
                        "Hello! I'm Ekko\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________");

        while (true) {
            try {
                String input = scanner.nextLine().trim();

                if (input.equals("bye")) {
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "Bye. Hope to see you again soon!\n" +
                                    "____________________________________________________________");
                    break;
                }

                handleInput(input);

            } catch (EkkoException e) {
                System.out.println(
                        "____________________________________________________________\n" +
                                " " + e.getMessage() + "\n" +
                                "____________________________________________________________");
            }
        }
    }
    public static void handleInput(String input) throws EkkoException {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            if (inputArr[0].equals("list")) {
                if (input.length() > 4) {
                    throw new EkkoException("Just type list!");
                }
                if (list.isEmpty()) {
                    throw new EkkoException("List is empty");
                }

                System.out.println(
                        "____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println(" " + (i + 1) + ". " + "[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "]" + " " + task);
                }

                System.out.println(
                        "____________________________________________________________");
                return;
            } else {
                if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event") || input.startsWith("mark") || input.startsWith("unmark") || input.equals("bye")) {
                    throw new EkkoException("Cannot be blank after command");
                }
                throw new EkkoException("Invalid command! available commands: todo deadline event list mark unmark bye");
            }
        }

        String description = inputArr[1];

        if (input.matches("mark \\d+")) {
            System.out.println(
                    "____________________________________________________________\n" +
                            "Nice! I've marked this task as done:");
            Integer i = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = list.get(i);
            task.markDone();
            System.out.println("[" + task.getStatusIcon() + "]" + " " + task);

            System.out.println(
                    "____________________________________________________________");
        } else if (input.matches("unmark \\d+")) {
            System.out.println(
                    "____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:");
            Integer i = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = list.get(i);
            task.markUndone();
            System.out.println("[" + task.getStatusIcon() + "]" + " " + task);

            System.out.println(
                    "____________________________________________________________");
        } else if (input.startsWith("todo")) {

            Task task = new ToDo(description);
            list.add(task);
            System.out.println(
                    "____________________________________________________________\n" +
                            "Got it. I've added this task:");
            System.out.println("[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "]" + " " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println(
                    "____________________________________________________________");
        } else if (input.startsWith("deadline")) {
            String[] descriptionArr = description.split("/by", 2);
            if (descriptionArr.length == 1) {
                throw new EkkoException("Invalid deadline format");
            }
            Task task = new Deadline(descriptionArr[0], descriptionArr[1]);
            list.add(task);
            System.out.println(
                    "____________________________________________________________\n" +
                            "Got it. I've added this task:");
            System.out.println("[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "]" + " " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println(
                    "____________________________________________________________");
        } else if (input.startsWith("event")) {

            String[] fromArr = description.split("/from", 2);
            if (fromArr.length == 1) {
                throw new EkkoException("Invalid event format");
            }
            String[] toArr = fromArr[1].split("/to", 2);
            if (toArr.length == 1) {
                throw new EkkoException("Invalid event format");
            }
            Task task = new Event(fromArr[0], toArr[0], toArr[1]);
            list.add(task);
            System.out.println(
                    "____________________________________________________________\n" +
                            "Got it. I've added this task:");
            System.out.println("[" + task.getCategory() + "]" + "[" + task.getStatusIcon() + "]" + " " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println(
                    "____________________________________________________________");
        } else {
            throw new EkkoException("Invalid command");
        }
    }
}
