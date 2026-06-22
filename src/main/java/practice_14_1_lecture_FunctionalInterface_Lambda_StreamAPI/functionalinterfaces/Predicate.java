package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

/*Predicate - используется для фильтрации данных.
- принимает один аргумент типа данных T*/

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
