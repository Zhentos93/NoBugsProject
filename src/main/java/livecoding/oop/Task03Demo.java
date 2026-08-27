package livecoding.oop;

import java.util.List;

// Абстрактный: общее имя есть, но универсальной формулы зарплаты нет
abstract class Employee {
    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Абстрактный: каждый вид сотрудника рассчитывает зарплату по своей формуле
    public abstract double calculateSalary();
}

class FixedSalaryEmployee extends Employee {
    private final double monthlySalary;

    public FixedSalaryEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary; // Для фиксированной оплаты возвращаем оклад
    }
}

class HourlyEmployee extends Employee {
    private final int hoursWorked;
    private final double hourlyRate;

    public HourlyEmployee(String name, int hoursWorked, double hourlyRate) {
        super(name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate; // Часы умножаем на ставку
    }
}

class PercentEmployee extends Employee {
    private final double salesAmount;
    private final double percent;

    public PercentEmployee(String name, double salesAmount, double percent) {
        super(name);
        this.salesAmount = salesAmount;
        this.percent = percent;
    }

    @Override
    public double calculateSalary() {
        return salesAmount * percent / 100; // Находим процент от продаж
    }
}

class SalaryService {
    public void payAll(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(
                    employee.getName() + " получает "
                            + employee.calculateSalary() // Вызывается реализация фактического типа
            );
        }
    }
}

public class Task03Demo {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new FixedSalaryEmployee("Анна", 3000),
                new HourlyEmployee("Борис", 160, 15),
                new PercentEmployee("Мария", 100_000, 5)
        );

        SalaryService salaryService = new SalaryService();
        salaryService.payAll(employees);
    }
}
