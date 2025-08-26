import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String FOLDER = "data";
    private static final String FILE = "data" + File.separator + "karl.txt";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(FILE);
        if (!dataFile.exists()) {
            new File(FOLDER).mkdirs();
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                if (type.equals("T")) {
                    Task t = new Todo(parts[2]);
                    if (isDone) t.markAsDone();
                    tasks.add(t);
                } else if (type.equals("D")) {
                    Task t = new Deadline(parts[2], parts[3]);
                    if (isDone) t.markAsDone();
                    tasks.add(t);
                } else if (type.equals("E")) {
                    Task t = new Event(parts[2], parts[3], parts[4]);
                    if (isDone) t.markAsDone();
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        File dataDir = new File(FOLDER);
        if (!dataDir.exists()) dataDir.mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (Task t : tasks) {
                String line = "";
                if (t instanceof Todo) {
                    line = String.format("T | %d | %s", t.isDone ? 1 : 0, t.description);
                } else if (t instanceof Deadline) {
                    line = String.format("D | %d | %s | %s", t.isDone ? 1 : 0, t.description, ((Deadline)t).by);
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = String.format("E | %d | %s | %s | %s", t.isDone ? 1 : 0, t.description, e.from, e.to);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
