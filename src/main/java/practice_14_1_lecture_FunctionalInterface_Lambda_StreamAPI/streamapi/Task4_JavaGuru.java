/*Задание 2: дана коллекция из объектов класса People (name, age, sex):
Arrays.asList(
    new People("Вася", 16, Sex.MAN),
    new People("Петя", 23, Sex.MAN),
    new People("Елена", 42, Sex.WOMAN),
    new People("Иван Иванович", 69, Sex.MAN))

1. Выбрать мужчин призывного возраста (от 18 до 27 лет)
2. Найти средний возраст мужчин
3. Найти кол-во людей условно трудоспособного возраста (от 18-55 лет женщины, мужчина 18-60 лет)*/

package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.streamapi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task4_JavaGuru {
    public static void main(String[] args) {
        List<People> peoples = Arrays.asList(
                new People("Маша", 16, Sex.WOMAN),
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMAN),
                new People("Артем ", 26, Sex.MAN),
                new People("Кирилл", 87, Sex.MAN),
                new People("Иван Иванович", 69, Sex.MAN));

        /*Выбрать мужчин призывного возраста (от 18 до 27 лет)*/
        List<People> peopleList1 = peoples.
                stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27 && p.getSex() == Sex.MAN)
                .collect(Collectors.toList());
        Stream<People> ps = peopleList1.stream();
        ps.forEach(System.out::println);
        //System.out.println(peopleList1); - верхняя строчка заменяет эту

        /*Найти средний возраст мужчин*/
        double averageAge = peoples
                .stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .mapToInt(People::getAge).average().getAsDouble();
        System.out.println(averageAge);

        /*Найти кол-во людей условно трудоспособного возраста (от 18-55 лет женщины, мужчина 18-60 лет)*/
        List<People> peopleList2 = peoples.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getSex() == Sex.MAN && p.getAge() < 60 || (p.getSex() == Sex.WOMAN) && p.getAge() < 55)
                .collect(Collectors.toList());
        System.out.println(peopleList2);

        /*Сортировка по возрасту, а если у кого-то и возраст одинаковый, то ещё и по имени*/
        peoples.stream()
                .sorted(new PeopleAgeComporator().thenComparing(new PeopleNameComporator()))
                .forEach(System.out::println);
    }
}

class PeopleAgeComporator implements Comparator<People> {

    @Override
    public int compare(People o1, People o2) {
        return o1.getAge() - o2.getAge();
    }
}

class PeopleNameComporator implements Comparator<People> {

    @Override
    public int compare(People o1, People o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class People {
    private String name;
    private int age;
    private Sex sex;

    public People(String name, int age, Sex sex) {
        this.name = name.trim();
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}

enum Sex {
    MAN, WOMAN;
}
