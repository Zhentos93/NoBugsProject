package сode_reading.JavaCodeReading;

class Box {
    int value;
}

public class A99 {
    static Box change(Box box) {
        box.value = 2;

        box = new Box();
        box.value = 3;

        return box;
    }

    public static void main(String[] args) {
        Box first = new Box();
        first.value = 1;

        Box second = change(first);

        System.out.println(first.value);
        System.out.println(second.value);
    }
}
