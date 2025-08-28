package karl;

import karl.command.Command;
import karl.exception.KarlException;
import karl.parser.Parser;
import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

import java.util.Scanner;

/**
 * Main chatbot class. Initializes all components and runs the main loop.
 */
public class Karl {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Creates a new Karl chatbot with the data file to load/store tasks.
     *
     * @param filePath The path to the data file.
     */
    public Karl(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KarlException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        isExit = false;
    }

    /**
     * Runs the chatbot, starting the input-read-process loop.
     */
    public void run() {
        ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = ui.readCommand(sc);
//                ui.printLine();

                // Parse input and return Command
                Command command = Parser.parse(input);

                // Execute the command
                command.execute(tasks, ui, storage);

                isExit = command.isExit();
            } catch (KarlException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        sc.close();
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args) {
        new Karl("data" + java.io.File.separator + "karl.txt").run();
    }
}
