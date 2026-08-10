package сode_reading.CodeReading_ObjectsAndReferences;

public class A8 {
    static StringBuilder change(StringBuilder builder) {
        builder.append("B");

        builder = new StringBuilder("C");

        return builder;
    }

    public static void main(String[] args) {
        StringBuilder first = new StringBuilder("A");

        StringBuilder second = change(first);

        System.out.println(first);
        System.out.println(second);
    }
}
