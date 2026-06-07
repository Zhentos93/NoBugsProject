/*Условие задачи:
    Напишите дженерик метод printArray, который принимает массив элементов любого типа и выводит
    каждый элемент массива на консоль.*/

package practice_13_2_homework_Exceptions_Generics.generics;

public class PrintArray {
    public <T> void printArray(T[] array) {

        for (T element : array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        PrintArray printArray = new PrintArray();

        printArray.printArray(new Integer[]{1, 2, 3});
        System.out.println(printArray);

        printArray.printArray(new String[]{"hello", "world"});
        System.out.println(printArray);
    }
}
