package livecoding.algorithms.seven;

public class E {

    public int countVowels(String s) {
        int sum = 0;
        String vowels = "aeiouаеёиоуыэюя";

        for (char c : s.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) >= 0) {
                sum++;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        E e = new E();

        System.out.println(e.countVowels("Hello World"));
        System.out.println(e.countVowels("Привет, Java"));
        System.out.println(e.countVowels("rhythm"));
        System.out.println(e.countVowels(""));
    }
}
