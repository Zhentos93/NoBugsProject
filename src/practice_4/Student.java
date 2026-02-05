package practice_4;

public class Student {
    final static int MAX_YEARS;
    static int studentCount;

    static {
        MAX_YEARS = 11;
        studentCount = 0;
    }

    int age;
    String name;

    Student(int someAge, String someName) {
        this.age = someAge;
        this.name = someName;
        studentCount++; // инкремент - увеличение значения на 1
    }

    private static void printMaxYears() {
        System.out.println(MAX_YEARS);
    }


}
