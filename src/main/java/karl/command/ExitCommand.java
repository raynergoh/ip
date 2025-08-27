package karl.command;

import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
