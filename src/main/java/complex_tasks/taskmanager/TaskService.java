package complex_tasks.taskmanager;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskService<T> {
    private final Map<T, Task<T>> tasks = new HashMap<>();

    public void addTask(Task<T> task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        if (task.getId() == null) {
            throw new IllegalArgumentException("Task id cannot be null");
        }

        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task priority cannot be null");
        }

        if (task.getDate() == null) {
            throw new IllegalArgumentException("Task date cannot be null");
        }

        if (tasks.containsKey(task.getId())) {
            throw new IllegalArgumentException("Task with this id already exists");
        }

        tasks.put(task.getId(), task);
    }

    public synchronized boolean removeTask(T id) {
        if (id == null) {
            throw new IllegalArgumentException("Task id cannot be null");
        }

        if (!tasks.containsKey(id)) {
            return false;
        }

        tasks.remove(id);
        return true;
    }

    public List<Task<T>> findByStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        return tasks.values().stream()
                .filter(task -> task.getStatus().equals(status))
                .toList();
    }

    public List<Task<T>> findByPriority(String priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }

        return tasks.values().stream()
                .filter(task -> task.getPriority().equals(priority))
                .toList();
    }

    public List<Task<T>> sortByDate() {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getDate))
                .toList();
    }
}
