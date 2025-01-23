import java.util.ArrayList;
import java.util.Scanner;

public class Ekko {
    public static ArrayList<String> list = new ArrayList<>();

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
                    System.out.println(" " + (i + 1) + ". " + list.get(i));
                }

                System.out.println(
                        "____________________________________________________________");
            } else {
                list.add(input);
                System.out.println(
                        "____________________________________________________________\n" +
                        " added: " + input + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}
