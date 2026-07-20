package complex_tasks.taskmanager;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    /**
     * Тестовые сценарии для TaskService.
     *
     * addTask():
     *
     * positive cases:
     *  Добавление валидной задачи:
     *   addTask(Task(1, "NEW", "HIGH", LocalDate.of(2026, 7, 20))).
     *      Проверка: findByStatus("NEW") возвращает список из одной задачи.
     * negative cases:
     *  Добавление задачи с уже существующим ID:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *      addTask(Task(1, "DONE", "LOW", date)) → IllegalArgumentException.
     *  Добавление null вместо задачи:
     *      addTask(null) → IllegalArgumentException.
     *  Добавление задачи с null ID:
     *      addTask(Task(null, "NEW", "HIGH", date)) → IllegalArgumentException.
     *  Добавление задачи с null status:
     *      addTask(Task(1, null, "HIGH", date)) → IllegalArgumentException.
     *  Добавление задачи с null priority:
     *      addTask(Task(1, "NEW", null, date)) → IllegalArgumentException.
     *  Добавление задачи с null date:
     *      addTask(Task(1, "NEW", "HIGH", null)) → IllegalArgumentException.
     *
     * removeTask():
     *
     * positive cases:
     *  Удаление существующей задачи:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *   removeTask(1).
     *      Проверка: findByStatus("NEW") возвращает пустой список.
     * corner cases:
     *  Удаление задачи с отсутствующим ID:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *   removeTask(100).
     *      Проверка: findByStatus("NEW") возвращает список из одной задачи.
     * negative cases:
     *  Удаление задачи с null ID:
     *      removeTask(null) → IllegalArgumentException.
     *
     * findByStatus():
     *
     * positive cases:
     *  Поиск задач по существующему статусу:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *   addTask(Task(2, "DONE", "LOW", date));
     *   addTask(Task(3, "NEW", "MEDIUM", date));
     *      findByStatus("NEW") → список из двух задач.
     * corner cases:
     *  Поиск по отсутствующему статусу:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *      findByStatus("DONE") → пустой список.
     * negative cases:
     *  Поиск с null status:
     *      findByStatus(null) → IllegalArgumentException.
     *
     * findByPriority():
     *
     * positive cases:
     *  Поиск задач по существующему приоритету:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *   addTask(Task(2, "DONE", "LOW", date));
     *   addTask(Task(3, "NEW", "HIGH", date));
     *      findByPriority("HIGH") → список из двух задач.
     * corner cases:
     *  Поиск по отсутствующему приоритету:
     *   addTask(Task(1, "NEW", "HIGH", date));
     *      findByPriority("LOW") → пустой список.
     * negative cases:
     *  Поиск с null priority:
     *      findByPriority(null) → IllegalArgumentException.
     *
     * sortByDate():
     *
     * positive cases:
     *  Сортировка задач по дате от ранней к поздней:
     *   addTask(Task(1, "NEW", "HIGH", LocalDate.of(2026, 7, 25)));
     *   addTask(Task(2, "DONE", "LOW", LocalDate.of(2026, 7, 20)));
     *   addTask(Task(3, "NEW", "MEDIUM", LocalDate.of(2026, 7, 22)));
     *      sortByDate() → задачи с ID 2, 3, 1.
     * corner cases:
     *  Сортировка при отсутствии задач:
     *      sortByDate() → пустой список.
     */

    /*addTask() - метод для добавления новой задачи в список*/

    @Test
    public void shouldAddValidTask() {
        // Добавление валидной задачи
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        List<Task<Integer>> actualResult = taskService.findByStatus("NEW");

        assertEquals(1, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForDuplicateTaskId() {
        // Добавление задачи с уже существующим ID
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        Task<Integer> duplicateTask = new Task<>(1, "DONE", "LOW",
                LocalDate.of(2026, 7, 20)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask(duplicateTask)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullTask() {
        // Добавление null вместо задачи
        TaskService<Integer> taskService = new TaskService<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask(null)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddingTaskWithNullId() {
        // Добавление задачи с null ID
        TaskService<Integer> taskService = new TaskService<>();

        Task<Integer> task = new Task<>(null, "DONE", "LOW",
                LocalDate.of(2026, 7, 20)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask(task)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddingTaskWithNullStatus() {
        // Добавление задачи с null status
        TaskService<Integer> taskService = new TaskService<>();

        Task<Integer> task = new Task<>(1, null, "LOW",
                LocalDate.of(2026, 7, 20)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask(task)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddingTaskWithNullPriority() {
        // Добавление задачи с null priority
        TaskService<Integer> taskService = new TaskService<>();

        Task<Integer> task = new Task<>(1, "NEW", null,
                LocalDate.of(2026, 7, 20)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask(task)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddingTaskWithNullDate() {
        // Добавление задачи с null date
        TaskService<Integer> taskService = new TaskService<>();

        Task<Integer> task = new Task<>(1, "NEW", "LOW",
                null
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.addTask(task)
        );
    }

    /*removeTask() - метод для удаления задачи */

    @Test
    public void shouldRemoveExistingTask() {
        // Удаление существующей задачи
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        taskService.removeTask(1);

        List<Task<Integer>> actualResult = taskService.findByStatus("NEW");

        assertEquals(0, actualResult.size());
    }

    @Test
    public void shouldThrowTaskNotFoundExceptionForNonExistingTaskId() {
        // Удаление задачи с отсутствующим ID
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        taskService.removeTask(100);

        List<Task<Integer>> actualResult = taskService.findByStatus("NEW");

        assertEquals(1, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullTaskId() {
        // Удаление задачи с null ID
        TaskService<Integer> taskService = new TaskService<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.removeTask(null)
        );
    }

    /*findByStatus() - поиск задач по статусу*/

    @Test
    public void shouldFindTasksByExistingStatus() {
        // Поиск задач по существующему статусу
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        taskService.addTask(new Task<>(2, "DONE", "LOW",
                LocalDate.of(2026, 7, 21)
        ));

        taskService.addTask(new Task<>(3, "NEW", "MEDIUM",
                LocalDate.of(2026, 7, 22)
        ));

        List<Task<Integer>> actualResult = taskService.findByStatus("NEW");

        assertEquals(2, actualResult.size());
    }

    @Test
    public void shouldReturnEmptyListForNonExistingStatus() {
        // Поиск по отсутствующему статусу
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        List<Task<Integer>> actualResult = taskService.findByStatus("DONE");

        assertEquals(0, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullStatus() {
        // Поиск с null status
        TaskService<Integer> taskService = new TaskService<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.findByStatus(null)
        );
    }

    /*findByPriority() - поиск задач по приоритету*/

    @Test
    public void shouldFindTasksByExistingPriority() {
        // Поиск задач по существующему приоритету
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        taskService.addTask(new Task<>(2, "DONE", "LOW",
                LocalDate.of(2026, 7, 21)
        ));

        taskService.addTask(new Task<>(3, "NEW", "HIGH",
                LocalDate.of(2026, 7, 22)
        ));

        List<Task<Integer>> actualResult = taskService.findByPriority("HIGH");

        assertEquals(2, actualResult.size());
    }

    @Test
    public void shouldReturnEmptyListForNonExistingPriority() {
        // Поиск по отсутствующему приоритету
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 20)
        ));

        List<Task<Integer>> actualResult = taskService.findByPriority("LOW");

        assertEquals(0, actualResult.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFindingTasksByNullPriority() {
        // Поиск с null priority
        TaskService<Integer> taskService = new TaskService<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> taskService.findByPriority(null)
        );
    }

    /*sortByDate() - сортировка задач по дате*/

    @Test
    public void shouldSortTasksByDateAscending() {
        // Сортировка задач по дате от ранней к поздней
        TaskService<Integer> taskService = new TaskService<>();

        taskService.addTask(new Task<>(1, "NEW", "HIGH",
                LocalDate.of(2026, 7, 25)
        ));

        taskService.addTask(new Task<>(2, "DONE", "LOW",
                LocalDate.of(2026, 7, 20)
        ));

        taskService.addTask(new Task<>(3, "NEW", "MEDIUM",
                LocalDate.of(2026, 7, 22)
        ));

        List<Task<Integer>> actualResult = taskService.sortByDate();

        assertEquals(3, actualResult.size());
        assertEquals(2, actualResult.get(0).getId());
        assertEquals(3, actualResult.get(1).getId());
        assertEquals(1, actualResult.get(2).getId());
    }

    @Test
    public void shouldReturnEmptyListWhenNoTasksExist() {
        // Сортировка при отсутствии задач
        TaskService<Integer> taskService = new TaskService<>();

        List<Task<Integer>> actualResult = taskService.sortByDate();

        assertEquals(0, actualResult.size());
    }
}
