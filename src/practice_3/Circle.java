package practice_3;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Circle {
    double radius;

    Circle(double someRadius) {
        this.radius = someRadius;
    }
    double getRadius() {
        return this.radius;
    }
    void setRadius(double newRadius) {
        this.radius = newRadius;
    }
    double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }
    double calculateCircumference() {
        return 2 * Math.PI * radius;
    }
    void print() {
        System.out.println("Площадь: " + calculateArea() + ", длина окружности: " + calculateCircumference());
    }
}
