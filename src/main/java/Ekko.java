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
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                        input + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}
