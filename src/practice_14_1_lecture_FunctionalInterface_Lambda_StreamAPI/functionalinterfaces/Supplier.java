package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

/*Supplier - используется для генерации значений.
- не принимает аргументов
- возвращает значение типа данных T*/

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
