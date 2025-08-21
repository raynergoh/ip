import java.util.Scanner;

public class Karl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[100]; // fixed-size array for tasks
        int taskCount = 0;               // keeps track of how many tasks are stored

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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                if (taskCount == 0) {
                    System.out.println(" (No tasks yet!)");
                }
                printLine();
                System.out.println();
            } else {
                // store the task in array
                tasks[taskCount] = input;
                taskCount++;

                printLine();
                System.out.println(" added: " + input);
                printLine();
                System.out.println();
            }
        }

        sc.close();
    }

    // helper formatter
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
