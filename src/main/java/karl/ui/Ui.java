package karl.ui;

import karl.task.Task;
import karl.task.TaskList;
import java.util.Scanner;

/**
 * Handles all user interactions for Karl.
 * Collects all messages in a buffer for GUI retrieval.
 */
public class Ui {
    private StringBuilder responseBuffer = new StringBuilder();

    // --- Methods for GUI support ---

    /** Clears the buffer before each response. */
    private void clearBuffer() {
        responseBuffer.setLength(0);
    }

    /** Returns the latest response as a string, then clears buffer. */
    public String getLastMessage() {
        String res = responseBuffer.toString();
        clearBuffer();
        return res;
    }

    public String showWelcome() {
        return "Hello! I'm Karl 🤖\nWhat can I do for you?\n";
    }

    public void showGoodbye() {
        clearBuffer();
        responseBuffer.append("Bye. Karl hopes to see you again!\n");
    }

    public void showError(String message) {
        clearBuffer();
        responseBuffer.append("Error: ").append(message).append("\n");
    }

    public void showLoadingError() {
        clearBuffer();
        responseBuffer.append("Error loading tasks. Starting with an empty list.\n");
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine().trim();
    }

    public void showAddedTask(Task task, int taskCount) {
        clearBuffer();
        responseBuffer.append("Got it. I've added this task:\n  ").append(task).append('\n');
        responseBuffer.append("Now you have ").append(taskCount)
                .append(" ").append((taskCount == 1) ? "task" : "tasks").append(" in the list.\n");
    }

    public void showRemovedTask(Task task, int taskCount) {
        clearBuffer();
        responseBuffer.append("Noted. I've removed this task:\n  ").append(task).append('\n');
        responseBuffer.append("Now you have ").append(taskCount)
                .append(" ").append((taskCount == 1) ? "task" : "tasks").append(" in the list.\n");
    }

    public void showMarkedTask(Task task) {
        clearBuffer();
        responseBuffer.append("Nice! I've marked this task as done:\n  ").append(task).append('\n');
    }

    public void showUnmarkedTask(Task task) {
        clearBuffer();
        responseBuffer.append("OK, I've marked this task as not done yet:\n  ").append(task).append('\n');
    }

    public void showTaskList(TaskList tasks) {
        clearBuffer();
        if (tasks.size() == 0) {
            responseBuffer.append("(No tasks yet!)\n");
        } else {
            responseBuffer.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                responseBuffer.append((i + 1)).append(". ").append(tasks.get(i)).append('\n');
            }
        }
    }

    // Add similar buffered output for all other reply types as needed
}
