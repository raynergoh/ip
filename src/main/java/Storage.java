import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private static final String FOLDER = "data";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws KarlException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File(filePath);

        if (!dataFile.exists()) {
            File folder = new File(FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return tasks; // empty list if no file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    System.out.println("Skipping malformed task line: " + line);
                    continue;
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                try {
                    if (type.equals("T")) {
                        Task t = new Todo(parts[2]);
                        if (isDone) t.markAsDone();
                        tasks.add(t);
                    } else if (type.equals("D")) {
                        if (parts.length < 4) {
                            System.out.println("Skipping malformed Deadline task line: " + line);
                            continue;
                        }
                        Task t = new Deadline(parts[2], parts[3]);
                        if (isDone) t.markAsDone();
                        tasks.add(t);
                    } else if (type.equals("E")) {
                        if (parts.length < 5) {
                            System.out.println("Skipping malformed Event task line: " + line);
                            continue;
                        }
                        Task t = new Event(parts[2], parts[3], parts[4]);
                        if (isDone) t.markAsDone();
                        tasks.add(t);
                    } else {
                        System.out.println("Skipping unknown task type: " + line);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping invalid task data: " + line);
                }
            }
        } catch (IOException e) {
            throw new KarlException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws KarlException {
        File dataDir = new File(FOLDER);
        if (!dataDir.exists()) dataDir.mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                String line = "";
                if (t instanceof Todo) {
                    line = String.format("T | %d | %s", t.isDone ? 1 : 0, t.description);
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    line = String.format("D | %d | %s | %s", t.isDone ? 1 : 0, t.description, d.by.toString());
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = String.format("E | %d | %s | %s | %s", t.isDone ? 1 : 0, t.description, e.from.toString(), e.to.toString());
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new KarlException("Error saving tasks: " + e.getMessage());
        }
    }
}
