package practice_4;

public class Main {
    public static void main(String[] args) {
        // создали переменные petya/kolya
        // присвоили переменной petya/kolya экземпляр класса Student, вызвав дефолтный конструктор

        System.out.println(Student.studentCount);

        Student petya = new Student(18, "Петя");

        int k = petya.age;

        System.out.println(Student.studentCount);

        Student kolya = new Student(20, "Коля");

        System.out.println(Student.studentCount);

    }
}
