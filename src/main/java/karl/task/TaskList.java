package karl.task;

import karl.exception.KarlException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Task> find(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
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
