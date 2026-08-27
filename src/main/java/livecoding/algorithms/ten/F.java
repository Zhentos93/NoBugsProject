package livecoding.algorithms.ten;

public class F {
    public int countWords(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0;
        }

        return s.trim().split("\\s+").length;
    }

    public static void main(String[] args) {
        F f = new F();

        System.out.println(f.countWords("Java is fun"));
        System.out.println(f.countWords("  Java   is fun  "));
        System.out.println(f.countWords("   "));
        System.out.println(f.countWords("one"));
        System.out.println(f.countWords(null));
    }
}
