/*Задача: разработать математические операции: сложение, умножение*/

package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.functionalinterfaces;

@FunctionalInterface
interface MathOperations {
    int operate (int a, int b);
}
