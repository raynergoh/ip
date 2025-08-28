package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

import java.util.List;

/**
 * Command to find tasks containing a keyword in their description.
 */
public class FindCommand implements Command {

    private final String keyword;

    public FindCommand(String input) throws KarlException {
        String userInput = input.trim();
        if (userInput.length() <= 4) { // "find" + space + keyword
            throw new KarlException("The find command requires a keyword.");
        }
        this.keyword = userInput.substring(5).trim(); // extract keyword after "find "
        if (this.keyword.isEmpty()) {
            throw new KarlException("The find command requires a keyword.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchedTasks = tasks.find(keyword);
        ui.printLine();
        if (matchedTasks.isEmpty()) {
            System.out.println("No matching tasks found for keyword: " + keyword);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchedTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchedTasks.get(i));
            }
        }
        ui.printLine();
        System.out.println();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
