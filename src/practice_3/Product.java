package practice_3;

public class Product {
    String name;
    int price;

    Product(String someName, int somePrice) {
        this.name = someName;
        this.price = somePrice;
    }
    String getName() {
        return this.name;
    }
    double getPrice() {
        return this.price;
    }
    void setPrice(int newPrice) {
        this.price = newPrice;
    }
    void applyDiscount(int discount) {
        double amount = price * discount / 100;
        price -= amount;
        System.out.println("Применена скидка " + discount + "%");
    }
    void printInfo() {
        System.out.println("Товар: " + this.name + ", цена с учётом скидки: " + this.price + " рублей");
    }
}
