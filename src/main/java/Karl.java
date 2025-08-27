import java.util.Scanner;

public class Karl {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

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

    public static void main(String[] args) {
        new Karl("data" + java.io.File.separator + "karl.txt").run();
    }
}
