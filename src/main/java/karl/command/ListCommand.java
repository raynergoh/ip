package karl.command;

import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
