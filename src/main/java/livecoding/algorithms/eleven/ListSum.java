package livecoding.algorithms.eleven;

/*
 * Задача №11. Сумма значений в списке
 *
 * Посчитать сумму всех чисел в List<Integer>.
 */

// Ошибок при решении нет

import java.util.List;

public class ListSum {
    public ListSum() {
    }

    public int sum(List<Integer> list) {
        int sum = 0;

        for (int num : list) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) {
        ListSum listSum = new ListSum();

        System.out.println(listSum.sum(List.of(1, 2, 3, 4)));
        System.out.println(listSum.sum(List.of(-5, 2, 3)));
        System.out.println(listSum.sum(List.of()));
    }
}
