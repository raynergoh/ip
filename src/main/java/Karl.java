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

            try {
                if (input.equalsIgnoreCase("bye")) {
                    printLine();
                    System.out.println(" Bye. Karl hopes to see you again soon!");
                    printLine();
                    break;

                } else if (input.equalsIgnoreCase("list")) {
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
                    int index = parseIndex(input, "mark");
                    Task task = getTask(tasks, index);
                    task.markAsDone();
                    printLine();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + task);
                    printLine();
                    System.out.println();

                } else if (input.startsWith("unmark ")) {
                    int index = parseIndex(input, "unmark");
                    Task task = getTask(tasks, index);
                    task.markAsNotDone();
                    printLine();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + task);
                    printLine();
                    System.out.println();

                } else if (input.startsWith("delete ")) {
                    int index = parseIndex(input, "delete");
                    Task removedTask = getTask(tasks, index);
                    tasks.remove(index);

                    printLine();
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now there are " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                    printLine();
                    System.out.println();
                } else if (input.startsWith("todo")) {
                    String desc = input.length() <= 4 ? "" : input.substring(4).trim();
                    if (desc.isEmpty()) {
                        throw new KarlException("I don't think the Todo description can be empty...");
                    }
                    Task newTask = new Todo(desc);
                    tasks.add(newTask);
                    printAddedTask(newTask, tasks.size());

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).split(" /by ", 2);
                    String desc = parts[0];
                    if (desc.trim().isEmpty()) {
                        throw new KarlException("I don't think the Deadline description can be empty...");
                    }
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new KarlException("Deadline must have a '/by' time specified.");
                    }
                    Task newTask = new Deadline(desc.trim(), parts[1].trim());
                    tasks.add(newTask);
                    printAddedTask(newTask, tasks.size());

                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split(" /from ", 2);
                    String desc = parts[0];
                    if (desc.trim().isEmpty()) {
                        throw new KarlException("I don't think the Event description can be empty...");
                    }
                    if (parts.length < 2) {
                        throw new KarlException("Event must have a '/from' time specified.");
                    }
                    String[] timeParts = parts[1].split(" /to ", 2);
                    if (timeParts.length < 2) {
                        throw new KarlException("Event must have a '/to' time specified.");
                    }
                    Task newTask = new Event(desc.trim(), timeParts[0].trim(), timeParts[1].trim());
                    tasks.add(newTask);
                    printAddedTask(newTask, tasks.size());

                } else {
                    // unknown command
                    printLine();
                    System.out.println(" Karl didn’t understand that command 😅");
                    printLine();
                    System.out.println();
                }
            } catch (KarlException e) {
                printLine();
                System.out.println(" " + e.getMessage());
                printLine();
                System.out.println();
            } catch (Exception e) {
                printLine();
                System.out.println(" Something went wrong: " + e.getMessage());
                printLine();
                System.out.println();
            }
        }

        sc.close();
    }

    private static int parseIndex(String input, String command) throws KarlException {
        String[] tokens = input.split(" ");
        if (tokens.length < 2) {
            throw new KarlException("You must specify the task number to " + command + ".");
        }
        try {
            int index = Integer.parseInt(tokens[1]) - 1;
            if (index < 0) {
                throw new KarlException("Task number must be a positive integer.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new KarlException("Invalid task number format.");
        }
    }

    private static Task getTask(ArrayList<Task> tasks, int index) throws KarlException {
        if (index >= tasks.size()) {
            throw new KarlException("Task number out of range.");
        }
        return tasks.get(index);
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
