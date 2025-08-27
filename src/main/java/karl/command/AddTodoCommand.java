package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.Task;
import karl.task.TaskList;
import karl.task.Todo;
import karl.ui.Ui;

public class AddTodoCommand implements Command {
    private final String description;

    public AddTodoCommand(String input) throws KarlException {
        this.description = input.substring(4).trim();
        if (this.description.isEmpty())
            throw new KarlException("The description of a todo cannot be empty.");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        Task todo = new Todo(description);
        tasks.add(todo);
        storage.saveTasks(tasks.getTasks());
        ui.showAddedTask(todo, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
