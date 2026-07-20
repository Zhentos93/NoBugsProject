package complex_tasks.studentgrades;

public class StudentGrade<T extends Number> {
    private final String name;
    private final String subject;
    private final T grade;

    public StudentGrade(String name, String subject, T grade) {
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public T getGrade() {
        return grade;
    }
}
