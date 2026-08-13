package livecoding.algorithms.twentyeight;

/*
 * Задача №28. Сравнение объектов Book
 *
 * Реализовать equals() и hashCode() в классе Book
 * по полям title и author.
 */

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Book {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { // Сравниваем ссылки на объекты
            return true;
        }

        if (!(o instanceof Book)) { // Проверяем тип объекта
            return false;
        }

        Book b = (Book) o; // Приводим Object к Book

        return title.equals(b.title)
                && author.equals(b.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author); // Вычисляем хеш по тем же полям
    }

    public static void main(String[] args) {
        Book first = new Book("Clean Code", "Robert Martin");
        Book second = new Book("Clean Code", "Robert Martin");
        Book third = new Book("Effective Java", "Joshua Bloch");

        System.out.println(first.equals(second));
        System.out.println(first.equals(third));
        System.out.println(first == second);
        System.out.println(first.hashCode() == second.hashCode());

        Set<Book> books = new HashSet<>();
        books.add(first);
        books.add(second);

        System.out.println(books.size());
    }
}
