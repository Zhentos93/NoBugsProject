package сode_reading.JavaCodeReading;

public class A57 {
    static abstract class Shape {
        abstract int area();
    }

    static class Square extends Shape {
        int side;

        Square(int side) {
            this.side = side;
        }

        int area() {
            return side * side;
        }
    }

    public static void main(String[] args) {
        Shape shape = new Square(4);

        System.out.println(shape.area());
    }
}
