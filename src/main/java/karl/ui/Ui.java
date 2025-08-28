package karl.ui;

import karl.task.Task;
import karl.task.TaskList;

import java.util.Scanner;

/**
 * Handles all user interactions via the console.
 * Responsible for reading user input and displaying messages.
 */
public class Ui {

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        printLine();
        System.out.println(" Hello! I'm Karl 🤖");
        System.out.println(" What can I do for you?");
        printLine();
    }

    /**
     * Displays a goodbye message when the chatbot exits.
     */
    public void showGoodbye() {
        printLine();
        System.out.println(" Bye. Karl hopes to see you again!");
//        printLine();
    }

    /**
     * Prints a horizontal divider line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to show.
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Displays a message indicating failure to load saved data.
     */
    public void showLoadingError() {
        System.out.println(" Error loading tasks. Starting with an empty list.");
    }

    /**
     * Reads a full line of user input from the given Scanner.
     *
     * @param sc Scanner connected to user input stream.
     * @return the trimmed input string.
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine().trim();
    }

    /**
     * Displays the confirmation message for task addition.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showAddedTask(Task task, int taskCount) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
//        printLine();
        System.out.println();
    }

    /**
     * Displays confirmation of a task removal.
     *
     * @param task The task that was removed.
     * @param taskCount The current number of tasks in the list.
     */
    public void showRemovedTask(Task task, int taskCount) {
        printLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
//        printLine();
        System.out.println();
    }

    /**
     * Displays confirmation that a task was marked done.
     *
     * @param task The task that was marked done.
     */
    public void showMarkedTask(Task task) {
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        printLine();
        System.out.println();
    }

    /**
     * Displays confirmation that a task was marked undone.
     *
     * @param task The task that was marked undone.
     */
    public void showUnmarkedTask(Task task) {
        printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
//        printLine();
        System.out.println();
    }

    /**
     * Displays the whole list of tasks to the user.
     *
     * @param tasks The task list to display.
     */
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
