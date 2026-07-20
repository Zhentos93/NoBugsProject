package complex_tasks.studentgrades;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GradeService<T extends Number> {
    List<StudentGrade<T>> grades = new ArrayList<>();

    public synchronized void addGrade(StudentGrade<T> grade) throws InvalidGradeException {
        if (grade == null) {
            throw new InvalidGradeException(
                    "The student's grade cannot be null"
            );
        }

        if (grade.getGrade() == null) {
            throw new InvalidGradeException(
                    "The grade cannot be null"
            );
        }

        if (grade.getGrade().doubleValue() < 0) {
            throw new InvalidGradeException(
                    "The grade cannot be negative"
            );
        }

        grades.add(grade);
    }

    public synchronized double calculateAverage(String subject) throws InvalidGradeException {
        if (subject == null) {
            throw new InvalidGradeException(
                    "The object cannot be null"
            );
        }

        double sum = 0.0;
        int count = 0;

        for (StudentGrade<T> studentGrade : grades) {
            if (subject.equals(studentGrade.getSubject())) {
                sum += studentGrade.getGrade().doubleValue();
                count++;
            }
        }

        if (count == 0) {
            throw new NoSuchElementException(
                    "No grade found for this subject"
            );
        }

        return sum / count;
    }
}
