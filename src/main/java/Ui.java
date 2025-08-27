import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        printLine();
        System.out.println(" Hello! I'm Karl 🤖");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public void showGoodbye() {
        printLine();
        System.out.println(" Bye. Karl hopes to see you again!");
//        printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showLoadingError() {
        System.out.println(" Error loading tasks. Starting with an empty list.");
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine().trim();
    }

    public void showAddedTask(Task task, int taskCount) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
//        printLine();
        System.out.println();
    }

    public void showRemovedTask(Task task, int taskCount) {
        printLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
//        printLine();
        System.out.println();
    }

    public void showMarkedTask(Task task) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        printLine();
        System.out.println();
    }

    public void showUnmarkedTask(Task task) {
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
//        printLine();
        System.out.println();
    }

    public void showTaskList(TaskList tasks) {
        printLine();
        if (tasks.size() == 0) {
            System.out.println(" (No tasks yet!)");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
//        printLine();
        System.out.println();
    }
}
