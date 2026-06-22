package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
