package livecoding.algorithms.thirtyone;

/*
 * Задача №31. Сравнение версий строк
 *
 * Сравнить строки вида "1.2.3" и "1.10.1"
 * по числовым частям версии.
 */

public class VersionComparator {

    public VersionComparator() {
    }

    int compare(String v1, String v2) {
        String[] a = v1.split("\\."); // Получаем части первой версии
        String[] b = v2.split("\\."); // Получаем части второй версии

        for (int i = 0; i < Math.max(a.length, b.length); i++) {
            int n1 = i < a.length ? Integer.parseInt(a[i]) : 0;
            int n2 = i < b.length ? Integer.parseInt(b[i]) : 0;

            if (n1 != n2) { // Нашли первую различающуюся часть
                return n1 - n2;
            }
        }

        return 0; // Все части версий равны
    }

    public static void main(String[] args) {
        VersionComparator comparator = new VersionComparator();

        System.out.println(comparator.compare("1.2.3", "1.10.1"));
        System.out.println(comparator.compare("2.0", "1.9.9"));
        System.out.println(comparator.compare("1.0", "1"));
        System.out.println(comparator.compare("1.0.1", "1"));
    }
}
