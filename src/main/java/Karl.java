import java.util.Scanner;
import java.util.ArrayList;

public class Karl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Intro
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
                // print tasks
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

            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5).trim();
                Task newTask = new Todo(desc);
                tasks.add(newTask);
                printAddedTask(newTask, tasks.size());

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                String description = parts[0].trim();
                String by = parts.length > 1 ? parts[1].trim() : "unspecified";
                Task newTask = new Deadline(description, by);
                tasks.add(newTask);
                printAddedTask(newTask, tasks.size());

            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from ", 2);
                String desc = parts[0].trim();

                String from = "unspecified";
                String to = "unspecified";

                if (parts.length > 1) {
                    String[] timeParts = parts[1].split(" /to ", 2);
                    from = timeParts[0].trim();

                    if (timeParts.length > 1) {
                        to = timeParts[1].trim();
                    }
                }

                Task newTask = new Event(desc, from, to);
                tasks.add(newTask);
                printAddedTask(newTask, tasks.size());

            } else {
                // unknown input
                printLine();
                System.out.println(" Karl didn’t understand that command 😅");
                printLine();
                System.out.println();
            }
        }

        sc.close();
    }

    // helper method for pretty printing
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printAddedTask(Task task, int size) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        printLine();
        System.out.println();
    }
}
