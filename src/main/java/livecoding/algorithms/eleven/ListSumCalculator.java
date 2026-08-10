package livecoding.algorithms.eleven;

/*
 * Задача №11. Сумма значений в списке
 *
 * Посчитать сумму всех чисел в List<Integer>.
 */

import java.util.List;

public class ListSumCalculator {

    public ListSumCalculator() {
    }

    int sum(List<Integer> list) {
        int sum = 0; // Начальное значение суммы

        for (int num : list) { // По очереди получаем числа из списка
            sum += num;        // Прибавляем текущее число к сумме
        }

        return sum; // Возвращаем сумму после полного прохода
    }

    public static void main(String[] args) {
        ListSumCalculator calculator = new ListSumCalculator();

        System.out.println(calculator.sum(List.of(1, 2, 3, 4)));
        System.out.println(calculator.sum(List.of(-5, 2, 3)));
        System.out.println(calculator.sum(List.of()));
    }
}
