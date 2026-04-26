/*Условие задачи:
    Напишите программу, которая пытается открыть файл с именем "data.txt".
    Если файл не найден, программа должна обработать исключение и вывести сообщение: "Файл не найден".*/

package practice_14_homework_Exceptions_Generics.exceptions.checked;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileNotFoundChecked {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("data.txt");
        }
        catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
        }
    }
}
