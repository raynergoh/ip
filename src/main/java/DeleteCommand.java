public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(String input) throws KarlException {
        this.index = parseIndex(input, "delete");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("The task number " + (index + 1) + " is out of range.");
        }
        Task removed = tasks.delete(index);
        storage.saveTasks(tasks.getTasks());
        ui.showRemovedTask(removed, tasks.size());
    }


    @Override
    public boolean isExit() {
        return false;
    }

    private int parseIndex(String input, String cmd) throws KarlException {
        String[] tokens = input.split(" ");
        if (tokens.length < 2) throw new KarlException("Please specify the task number to " + cmd + ".");
        try {
            int idx = Integer.parseInt(tokens[1]) - 1;
            if (idx < 0) throw new KarlException("Task number must be positive.");
            return idx;
        } catch (NumberFormatException e) {
            throw new KarlException("Invalid number format.");
        }
    }
}
