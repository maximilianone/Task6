package task3.views;

import task3.model.Book;

import java.util.List;

public class LibraryView {
    void showLibrary(List<Book> library){
        for (Book book: library){
            System.out.println(book);
        }
    }
}
