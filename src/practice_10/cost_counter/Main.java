package practice_10.cost_counter;

public class Main {
    public static void main(String[] args) {
        CostCounter costCounter = new CostCounter();
        costCounter.addCosts(1, 123.12);
        costCounter.addCosts(2, 1233.12);
        costCounter.addCosts(3, 3737.1);
        costCounter.addCosts(4, 344.2);
        costCounter.addCosts(5, 1233.2);

        System.out.println(costCounter.getCosts(3));
        System.out.println(costCounter.minCostsPerMonth());
    }
}