/*Задача: разработать математические операции: сложение, умножение*/

package express_java.practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

@FunctionalInterface
interface MathOperations {
    int operate (int a, int b);
}
