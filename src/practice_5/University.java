package practice_5;

public class University {
    static String universityName = "MGU";
    final int studentID;
    String studentName;

    University (int studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }
    static void changeUniversityName(String newName) {
        universityName = newName;
    }
    String getStudentName() {
        return this.studentName;
    }
    public void printStudentInfo() {
        System.out.println("Имя студента: " + this.studentName + ", ID студента: " + this.studentID + ", университет: " + University.universityName);
    }
}
