public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(String input) throws KarlException {
        this.index = parseIndex(input, "unmark");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("The task number " + (index + 1) + " is out of range.");
        }
        tasks.unmark(index);
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkedTask(tasks.get(index));
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

