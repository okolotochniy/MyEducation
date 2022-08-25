package aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Value("BookBooBook")
    private String bookName;
    @Value("Huauthor")
    private String author;
    @Value("1111")
    private int yearOfWriting;

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }
}
