package livecoding.algorithms.fiftyfour;

/*
 * Задача №54. Удалить гласные
 *
 * Удалить все английские гласные из строки.
 */

public class VowelRemover {

    public VowelRemover() {
    }

    public String removeVowels(String s) {
        return s.replaceAll("(?i)[aeiou]", "");
    }

    public static void main(String[] args) {
        VowelRemover remover = new VowelRemover();

        System.out.println(
                remover.removeVowels("Hello World")
        );
        System.out.println(
                remover.removeVowels("AEIOU")
        );
        System.out.println(
                remover.removeVowels("rhythm")
        );
        System.out.println(
                remover.removeVowels("Java 17")
        );
        System.out.println(
                remover.removeVowels("")
        );
    }
}
