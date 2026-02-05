package practice_5;

public class Library {
    private String bookTitle;
    protected String author;
    int year;
    public String category;

    public Library(String bookTitle, String author, int year, String category) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.year = year;
        this.category = category;
    }
    String getBookTitle() {
        return this.bookTitle;
    }
    void setBookTitle(String newBookTitle) {
        this.bookTitle = newBookTitle;
    }
    String getAuthor() {
        return this.author;
    }
    void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }
    int getYear() {
        return this.year;
    }
    void setYear(int newYear) {
        this.year = newYear;
    }
    String getCategory() {
        return this.category;
    }
    void setCategory(String newCategory) {
        this.category = newCategory;
    }
}
