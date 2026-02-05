package practice_3;

public class Rectangle {
    double width;
    double height;

    Rectangle (double someWidth, double someHeight) {
        this.width = someWidth;
        this.height = someHeight;
    }
    double getWidth() {
        return this.width;
    }
    double getHeight() {
        return this.height;
    }
    public void setWidth(double newWidth){
        this.width = newWidth;
    }
    public double calculateArea() {
        return this.width * this.height;
    }
    void print() {
        System.out.println("Площадь прямоугольника: " + calculateArea());
    }
}
