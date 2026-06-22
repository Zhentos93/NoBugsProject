package practice_6_2_homework_StaticFinalAccessModifiers;

public class MathConstants {
    static final double PI = 3.14159;
    final double E = 2.71828;

    static double calculateCircleArea(double r) {
        return PI * Math.pow(r, 2);
    }
    static double calculateCircumference(double r) {
        return 2 * PI * r;
    }
}

