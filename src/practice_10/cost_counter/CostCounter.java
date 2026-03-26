package practice_10.cost_counter;

 /*
 Пример 1: разработать приложение учета расходов, в котором храним расходы по месяцам.
 Иметь доступ к расходам + считать месяц с минимальным расходом
 */

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CostCounter {
    // массив, индекс = номер месяца
    private ArrayList<Double> costsPerMonth;

    public CostCounter() {
        this.costsPerMonth = new ArrayList<>();
    }

    // метод добавления значения по номеру месяца (от 1 до 12) и расходам в этот месяц
    public void addCosts(int month, Double costs) {
        costsPerMonth.add(month - 1, costs);
    }

    // метод получения значения расхода по месяцу
    public Double getCosts(int month) {
        return costsPerMonth.get(month - 1);
    }

    // считать минимальный расход за месяц
    public Double minCostsPerMonth() {
        AtomicReference<Double> min = new AtomicReference<>(costsPerMonth.getFirst());
        costsPerMonth.forEach(
                costsPerMonth -> {
                    if (costsPerMonth < min.get()) {
                        min.set(costsPerMonth);
                    }
                }
        );
    return min.get();
    };

/*    считать месяц с минимальным расходом
    public int getMinCostMonth() {
        double min = costsPerMonth.getFirst();
        costsPerMonth.forEach(
                costs -> {

                }
        );
    }*/
}