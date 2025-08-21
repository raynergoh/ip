import java.util.Scanner;
import java.util.ArrayList;

public class Karl {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>(); // dynamic list for tasks

        // chatbot intro
        printLine();
        System.out.println(" Hello! I'm Karl 🤖");
        System.out.println(" What can I do for you?");
        printLine();
        System.out.println();

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println(" Bye. Karl hopes to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                // print all tasks
                printLine();
                if (tasks.isEmpty()) {
                    System.out.println(" (No tasks yet!)");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
                printLine();
                System.out.println();
            } else {
                // add new task
                tasks.add(input);
                printLine();
                System.out.println(" added: " + input);
                printLine();
                System.out.println();
            }
        }

        sc.close();
    }

    // helper method for formatting
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
