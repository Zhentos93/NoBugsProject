package livecoding.oophomework;

/*Система работает с разными типами сотрудников:

сотрудник с фиксированным окладом
сотрудник с почасовой оплатой
сотрудник с процентом от продаж

У каждого сотрудника есть имя.

Правила расчета зарплаты:
У каждого сотрудника в зависимости от типа есть поля:
для фиксированная зарплата — месячный оклад
для почасовая — количество часов × ставка
для процентная — процент × сумма продаж

Нужно реализовать систему, которая может выплатить зарплату всем сотрудникам.

Требование:

Метод, который выплачивает зарплату, должен работать со списком сотрудников, не зная их конкретный тип.
Каждый тип сотрудника должен сам рассчитывать свою зарплату.

Метод должен вывести:
Анна получила 3000
Борис получил 2400
Мария получила 5000*/

import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculate();
}

class FixedSalary extends Employee {
    private double monthlySalary;

    public FixedSalary(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculate() {
        return monthlySalary;
    }
}

class HourlySalary extends Employee {
    private int hours;
    private double hourlyRate;

    public HourlySalary(String name, int hours, double hourlyRate) {
        super(name);
        this.hours = hours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculate() {
        return hours * hourlyRate;
    }
}

class PercentSalary extends Employee {
    private int percent;
    private double sum;

    public PercentSalary(String name, int percent, double sum) {
        super(name);
        this.percent = percent;
        this.sum = sum;
    }

    @Override
    public double calculate() {
        return percent * sum / 100;
    }
}

class SystemForSalary {
    private List<Employee> employees;

    public SystemForSalary(List<Employee> employees) {
        this.employees = employees;
    }

    public void payAll() {
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " получает " + employee.calculate());
        }
    }
}

public class Task3 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new FixedSalary("Анна", 3000));
        employees.add(new HourlySalary("Борис", 4, 600));
        employees.add(new PercentSalary("Мария", 5, 100000));

        SystemForSalary systemForSalary = new SystemForSalary(employees);

        systemForSalary.payAll();
    }
}
