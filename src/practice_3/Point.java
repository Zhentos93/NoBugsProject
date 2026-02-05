package practice_3;

public class Point {
    double x;
    double y;

    Point(double someX, double someY) {
        this.x = someX;
        this.y = someY;
    }
    double getX() {
        return this.x;
    }
    double getY() {
        return this.y;
    }
    public void setX(double newX) {
        this.x = newX;
    }
    void print() {
        System.out.println("Координаты: X = " + this.x + ", Y = " + this.y); // - 1 вариант, рабочий
        //System.out.println(this.x, this.y); // 2 вариант - ругается
        //System.out.println(this.x + " " + this.y); // - 3 вариант, рабочий
    }
}
