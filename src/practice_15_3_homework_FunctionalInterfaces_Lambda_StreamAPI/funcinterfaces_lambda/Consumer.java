package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

@FunctionalInterface
public interface Consumer<T> {
    public void accept(T t);
}
