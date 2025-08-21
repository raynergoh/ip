import java.util.Scanner;
import java.util.ArrayList;

public class Karl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(); // dynamic list of Task objects

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
                // print task list
                printLine();
                if (tasks.isEmpty()) {
                    System.out.println(" (No tasks yet!)");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
                printLine();
                System.out.println();
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    printLine();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + task);
                    printLine();
                } catch (Exception e) {
                    System.out.println(" Invalid task number!");
                }
                System.out.println();
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = tasks.get(index);
                    task.markAsNotDone();
                    printLine();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + task);
                    printLine();
                } catch (Exception e) {
                    System.out.println(" Invalid task number!");
                }
                System.out.println();
            } else {
                // add new task
                Task newTask = new Task(input);
                tasks.add(newTask);
                printLine();
                System.out.println(" added: " + newTask);
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
