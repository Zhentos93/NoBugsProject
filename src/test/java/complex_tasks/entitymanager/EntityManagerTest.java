package complex_tasks.entitymanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerTest {
    /**
     * Тесты EntityManager:
     * positive cases:
     *  Добавление элемента в пустой менеджер:
     *      add(Student("Коля", 17, true)) → элемент присутствует в getAll(), размер равен 1 +
     *  Добавление элемента в непустой менеджер:
     *      размер до добавления: 2, add(Student("Коля", 17, true)) → элемент присутствует в getAll(), размер равен 3 +
     *  Удаление существующего элемента:
     *      remove(Student("Коля", 17, true)) → true +
     *  Получение копии коллекции:
     *      изменение списка, полученного через getAll() → исходная коллекция менеджера не изменяется +
     *  Фильтрация по возрасту:
     *      возраст: 17, диапазон: 16–18 → элемент присутствует в результате +
     *  Фильтрация по имени:
     *      имя: "Коля", искомое имя: "Коля" → элемент присутствует в результате +
     *  Фильтрация по активности:
     *      active = true, filterByActive(true) → только активные элементы +
     *      active = false, filterByActive(false) → только неактивные элементы +
     * corner cases:
     *  Фильтрация по возрасту. Минимальная и максимальная границы возрастного диапазона:
     *      возраст: 16, диапазон: 16–18 → элемент присутствует в результате +
     *      возраст: 18, диапазон: 16–18 → элемент присутствует в результате +
     * negative cases:
     *  Удаление отсутствующего элемента:
     *      remove(Student("Коля", 17, true)) → false +
     *  Фильтрация по возрасту без совпадений:
     *      возраст: 20, диапазон: 16–18 → пустой список +
     *  Фильтрация по имени без совпадений:
     *      имя: "Коля", искомое имя: "Оля" → пустой список +
     *  Фильтрация по активности без совпадений:
     *      только active = true, filterByActive(false) → пустой список +
     */

    /*add() - тесты на добавление элементов*/

    @Test
    public void shouldAddElementToEmptyManager() {
        // Добавление элемента в пустой менеджер
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student("Коля", 17, true);

        manager.add(expectedStudent);

        List<Student> actualResult = manager.getAll();

        assertEquals(1, actualResult.size());
        assertTrue(actualResult.contains(expectedStudent));
    }

    @Test
    public void shouldAddElementToNonEmptyManager() {
        // Добавление элемента в непустой менеджер
        EntityManager<Student> manager = new EntityManager<>();

        manager.add(new Student("Олег", 20, true));
        manager.add(new Student("Алина", 42, true));

        Student expectedStudent = new Student("Коля", 17, true);

        manager.add(expectedStudent);

        List<Student> actualResult = manager.getAll();

        assertEquals(3, actualResult.size());
        assertTrue(actualResult.contains(expectedStudent));
    }

    /*remove() - тесты на удаление элементов*/

    @Test
    public void shouldRemoveExistingElement() {
        // Удаление существующего элемента
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student("Коля", 17, true);

        manager.add(expectedStudent);

        boolean actualResult = manager.remove(expectedStudent);

        assertTrue(actualResult);
        assertFalse(manager.getAll().contains(expectedStudent));
    }

    @Test
    public void shouldReturnFalseWhenRemovingMissingEntity() {
        // Удаление отсутствующего элемента
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student("Коля", 17, true);

        boolean actualResult = manager.remove(expectedStudent);

        assertFalse(actualResult);
    }

    /*getAll()*/

    @Test
    public void shouldReturnCopyOfCollection() {
        // Получение копии коллекции
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student("Коля", 17, true);

        manager.add(expectedStudent);

        List<Student> actualResult = manager.getAll();

        assertThrows(
                UnsupportedOperationException.class,
                () -> actualResult.add(new Student("Оля", 18, true))
        );
    }

    /*filterByAge()*/

    @ParameterizedTest
    @CsvSource({
            // positive cases
            // возраст: 17, диапазон: 16–18 → элемент присутствует в результате
            "17, 16, 18, true",

            // corner cases
            // возраст: 16, диапазон: 16–18 → элемент присутствует в результате
            "16, 16, 18, true",
            // возраст: 18, диапазон: 16–18 → элемент присутствует в результате
            "18, 16, 18, true",

            // negative cases
            // возраст: 20, диапазон: 16–18 → пустой список
            "20, 16, 18, false"
    })
    public void shouldFilterEntitiesByAge(int studentAge, int minAge, int maxAge, boolean expectedResult) {
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student("Коля", studentAge, true);

        manager.add(expectedStudent);

        List<Student> actualResult = manager.filterByAge(minAge, maxAge);

        assertEquals(expectedResult, actualResult.contains(expectedStudent));
    }

    /*filterByName()*/

    @ParameterizedTest
    @CsvSource({
            // positive cases
            // имя: "Коля", искомое имя: "Коля" → элемент присутствует в результате
            "Коля, Коля, true",

            // negative cases
            // имя: "Коля", искомое имя: "Оля" → пустой список
            "Коля, Оля, false"
    })
    public void shouldFilterEntitiesByName(String studentName, String searchedName, boolean expectedResult) {
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student(studentName, 17, true);

        manager.add(expectedStudent);

        List<Student> actualResult = manager.filterByName(searchedName);

        assertEquals(expectedResult, actualResult.contains(expectedStudent));
    }

    /*filterByActive()*/
    @ParameterizedTest
    @CsvSource({
            // positive cases
            // studentActivity = true, filterByActive(true) → только активные элементы (true)
            "true, true, true",
            // studentActivity = false, filterByActive(false) → только неактивные элементы (true)
            "false, false, true",

            // negative cases
            // только active = true, filterByActive(false) → пустой список
            "true, false, false"
    })
    public void shouldFilterEntitiesByActivity(boolean studentActivity, boolean searchedActivity,
                                               boolean expectedResult) {
        EntityManager<Student> manager = new EntityManager<>();
        Student expectedStudent = new Student("Коля", 17, studentActivity);

        manager.add(expectedStudent);

        List<Student> actualResult = manager.filterByActive(searchedActivity);

        assertEquals(expectedResult, actualResult.contains(expectedStudent));
    }
}
