package сode_reading.CodeReading_ObjectsAndReferences;

public class A7 {
    public static void main(String[] args) {
        StringBuilder first = new StringBuilder("A");
        StringBuilder second = first;

        second.append("B");

        second = new StringBuilder("C");
        first.append("D");

        System.out.println(first);
        System.out.println(second);
    }
}
