public interface Command {
    /**
     * Executes the command.
     * @param tasks The TaskList to operate on
     * @param ui The Ui instance for interaction
     * @param storage The Storage to save/load data
     * @throws KarlException when errors occur
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException;

    /**
     * @return true if this command signals exit (e.g., 'bye' command)
     */
    boolean isExit();
}
