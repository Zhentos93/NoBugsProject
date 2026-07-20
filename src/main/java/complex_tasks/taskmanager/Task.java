package complex_tasks.taskmanager;

import java.time.LocalDate;
import java.util.Objects;

public class Task<T> {
    private final T id;
    private final String status;
    private final String priority;
    private final LocalDate date;

    public Task(T id, String status, String priority, LocalDate date) {
        this.id = id;
        this.status = status;
        this.priority = priority;
        this.date = date;
    }

    public T getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task<?> task = (Task<?>) o;
        return Objects.equals(id, task.id) && Objects.equals(status, task.status)
                && Objects.equals(priority, task.priority) && Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, priority, date);
    }
}
