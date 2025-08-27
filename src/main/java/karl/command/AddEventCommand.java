package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Event;
import karl.task.Task;
import karl.task.TaskList;
import karl.ui.Ui;

public class AddEventCommand implements Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String input) throws KarlException {
        String[] parts = input.substring(5).split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty())
            throw new KarlException("Event format: event DESCRIPTION /from DATE /to DATE (yyyy-MM-dd)");
        this.description = parts[0].trim();

        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty())
            throw new KarlException("Event must specify /from and /to dates.");
        this.from = timeParts[0].trim();
        this.to = timeParts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        try {
            Task event = new Event(description, from, to);
            tasks.add(event);
            storage.saveTasks(tasks.getTasks());
            ui.showAddedTask(event, tasks.size());
        } catch (Exception e) {
            throw new KarlException("Please enter event dates in yyyy-MM-dd format.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
