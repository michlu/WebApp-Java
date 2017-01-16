package info.nowin.model;

/**
 * @author Michlu
 * @sience 2017-01-06
 */
public class Book {
    private String isbn;
    private String title;
    private String description;
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Book(){}
    public Book(String isbn, String title, String desc){
        this.isbn = isbn;
        this.title = title;
        description = desc;
    }
}
