package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

/*Function - чаще всего используют для преобразования данных.
- принимает 1 тип данных T,
- возвращает тип данных R*/

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
