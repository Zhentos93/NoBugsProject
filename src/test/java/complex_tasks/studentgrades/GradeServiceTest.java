package complex_tasks.studentgrades;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GradeServiceTest {
    /**
     * Тесты сервиса оценок студентов:
     * positive cases:
     *  Добавление валидной оценки:
     *      StudentGrade<Integer>("Ivan", "Math", 5) → оценка добавлена +
     *  Расчёт среднего значения по предмету: +
     *      Math: 4
     *      Math: 5
     *      History: 2
     *      calculateAverage("Math") → 4.5 +
     * negative cases:
     *  Значение оценки равно null: +
     *      StudentGrade<Integer>("Ivan", "Math", null) → InvalidGradeException("The grade cannot be null") +
     *  Оценка отрицательная: +
     *      StudentGrade<Integer>("Ivan", "Math", -1) → InvalidGradeException("The grade cannot be negative") +
     *  Оценок по предмету нет: +
     *      Math: 5
     *      calculateAverage("Physics") → NoSuchElementException("No grade found for this subject") +
     */

    /*addGrade() - метод добавления оценок*/

    @Test
    public void shouldAddValidGrade() throws InvalidGradeException {
        // Добавление валидной оценки
        GradeService<Integer> gradeService = new GradeService<>();

        StudentGrade<Integer> ivan = new StudentGrade<>("Ivan", "Math", 5);

        gradeService.addGrade(ivan);

        double actualResult = gradeService.calculateAverage("Math");

        assertEquals(5, actualResult);
    }

    @Test
    public void shouldThrowInvalidGradeExceptionForNullGradeValue() {
        // Значение оценки равно null
        GradeService<Integer> gradeService = new GradeService<>();

        StudentGrade<Integer> ivan = new StudentGrade<>("Ivan", "Math", null);

        assertThrows(
                InvalidGradeException.class,
                () -> gradeService.addGrade(ivan),
                "The grade cannot be null"
        );
    }

    @Test
    public void shouldThrowInvalidGradeExceptionForNegativeGradeValue() throws InvalidGradeException {
        // Оценка отрицательная
        GradeService<Integer> gradeService = new GradeService<>();

        StudentGrade<Integer> ivan = new StudentGrade<>("Ivan", "Math", -1);

        assertThrows(
                InvalidGradeException.class,
                () -> gradeService.addGrade(ivan),
                "The grade cannot be negative"
        );
    }

    /*calculateAverage() - метод расчёта среднего значения оценок по конкретному предмету*/

    @Test
    public void shouldCalculateAverageForMathSubject() throws InvalidGradeException {
        // Расчёт среднего значения по предмету Math
        GradeService<Integer> gradeService = new GradeService<>();

        StudentGrade<Integer> ivan = new StudentGrade<>("Ivan", "Math", 4);
        StudentGrade<Integer> alina = new StudentGrade<>("Alina", "Math", 5);
        StudentGrade<Integer> alex = new StudentGrade<>("Alex", "History", 2);

        gradeService.addGrade(ivan);
        gradeService.addGrade(alina);
        gradeService.addGrade(alex);

        double actualResult = gradeService.calculateAverage("Math");

        assertEquals(4.5, actualResult);
    }

    @Test
    public void shouldThrowExceptionWhenNoGradesExistForSubject() throws InvalidGradeException {
        // Оценок по предмету нет
        GradeService<Integer> gradeService = new GradeService<>();

        StudentGrade<Integer> ivan = new StudentGrade<>("Ivan", "Math", 5);

        gradeService.addGrade(ivan);

        assertThrows(
                NoSuchElementException.class,
                () -> gradeService.calculateAverage("Physics"),
                "No grade found for this subject"
        );
    }
}
