import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("Invalid task index");
        }
        return tasks.remove(index);
    }


    public void mark(int index) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("Invalid task index");
        }
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) throws KarlException {
        if (index < 0 || index >= tasks.size()) {
            throw new KarlException("Invalid task index");
        }
        tasks.get(index).markAsNotDone();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
