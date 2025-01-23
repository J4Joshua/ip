import java.util.ArrayList;
import java.util.Scanner;

public class Ekko {
    public static ArrayList<Task> list = new ArrayList<>();

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
            return this.description + " (by:" + date + ")";
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
            return this.description + " (from:" + from + "to:" + to + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "____________________________________________________________\n" +
                " Hello! I'm Ekko\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println(
                        "____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println(" " + (i + 1) + ". " + "[" +  task.getCategory() + "]" + "[" +  task.getStatusIcon() + "]" + " " + task);
                }

                System.out.println(
                        "____________________________________________________________");
            } else if (input.matches("mark \\d+")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:");
                Integer i = Integer.parseInt(input.split(" ")[1]) - 1;
                Task task = list.get(i);
                task.markDone();
                System.out.println("[" +  task.getStatusIcon() + "]" + " " + task);

                System.out.println(
                        "____________________________________________________________");
            } else if (input.matches("unmark \\d+")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:");
                Integer i = Integer.parseInt(input.split(" ")[1]) - 1;
                Task task = list.get(i);
                task.markUndone();
                System.out.println("[" +  task.getStatusIcon() + "]" + " " + task);

                System.out.println(
                        "____________________________________________________________");
            } else if (input.startsWith("todo")) {
                System.out.println(
                        "____________________________________________________________\n" +
                                "Got it. I've added this task:");

                Task task = new ToDo(input.split(" ",2)[1]);
                list.add(task);

                System.out.println("[" +  task.getCategory() + "]" + "[" +  task.getStatusIcon() + "]" + " " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println(
                        "____________________________________________________________");
            }  else if (input.startsWith("deadline")) {
                System.out.println(
                        "____________________________________________________________\n" +
                        "Got it. I've added this task:");

                Task task = new Deadline(input.split(" ",2)[1].split("/by ",2)[0], input.split(" ",2)[1].split("/by",2)[1]);
                list.add(task);

                System.out.println("[" +  task.getCategory() + "]" + "[" +  task.getStatusIcon() + "]" + " " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println(
                        "____________________________________________________________");
            } else if (input.startsWith("event")) {
                System.out.println(
                        "____________________________________________________________\n" +
                                "Got it. I've added this task:");

                Task task = new Event(input.split(" ",2)[1].split("/from ",2)[0], input.split(" ",2)[1].split("/from",2)[1].split("/to",2)[0], input.split(" ",2)[1].split("/from",2)[1].split("/to",2)[1]);
                list.add(task);

                System.out.println("[" +  task.getCategory() + "]" + "[" +  task.getStatusIcon() + "]" + " " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println(
                        "____________________________________________________________");
            } else {
                list.add(new Task(input));
                System.out.println(
                        "____________________________________________________________\n" +
                        " added: " + input + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}
