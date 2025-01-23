import java.util.ArrayList;
import java.util.Scanner;

public class Ekko {
    public static ArrayList<Task> list = new ArrayList<>();

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
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
                    System.out.println(" " + (i + 1) + ". " + "[" +  task.getStatusIcon() + "]" + " " + task);
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
            }else {
                list.add(new Task(input));
                System.out.println(
                        "____________________________________________________________\n" +
                        " added: " + input + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}
