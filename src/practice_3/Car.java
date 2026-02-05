package practice_3;

public class Car {
    String brand;
    int year;

    Car (String someBrand, int someYear) {
        this.brand = someBrand;
        this.year = someYear;
    }
    String getBrand() {
        return this.brand;
    }
    int getYear() {
        return this.year;
    }
    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }
    public void setYear(int newYear) {
        this.year = newYear;
    }
    void print() {
        System.out.println("Марка: " + this.brand + ", год выпуска: " + this.year);
    }
}
