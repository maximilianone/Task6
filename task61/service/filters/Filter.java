package task3.service.filters;


import task3.model.Book;

import java.util.ArrayList;
import java.util.List;

abstract class Filter {
    public List<Book> filter(List<Book> library) {
        List<Book> resultLibrary = new ArrayList<>();
        for (Book book : library) {
            if (checkCondition(book)) {
                resultLibrary.add(book);
            }
        }
        return resultLibrary;
    }

    abstract boolean checkCondition(Book book);
}
