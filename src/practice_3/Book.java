package practice_3;

public class Book {
    String title;
    String author;

    Book(String someTitle, String someAuthor) {
        this.title = someTitle;
        this.author = someAuthor;
    }
    String getTitle() {
        return this.title;
    }
    String getAuthor() {
        return this.author;
    }
    void setTitle(String newTitle) {
        this.title = newTitle;
    }
    public void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }
    void printInfo() {
        System.out.println("Название книги: " + this.title + ", автор: " + this.author);
    }
}
