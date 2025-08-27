package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Deadline;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

public class AddDeadlineCommand implements Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String input) throws KarlException {
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty())
            throw new KarlException("Deadline format: deadline DESCRIPTION /by DATE (yyyy-MM-dd)");
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        try {
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            storage.saveTasks(tasks.getTasks());
            ui.showAddedTask(deadline, tasks.size());
        } catch (Exception e) {
            throw new KarlException("Please enter the deadline date in yyyy-MM-dd format.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
