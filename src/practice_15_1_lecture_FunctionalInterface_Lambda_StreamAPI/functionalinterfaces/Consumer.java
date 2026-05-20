package practice_15_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

/*Consumer - выполнение действий.
- принимает один аргумент типа данных T.
- не возвращает значение*/

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
