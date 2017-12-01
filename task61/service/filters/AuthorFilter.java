package task3.service.filters;

import task3.model.Book;


public class AuthorFilter extends Filter {
    private final String author;

    public AuthorFilter(String author) {
        this.author = author;
    }

    @Override
    public boolean checkCondition(Book book) {
        return author.equals(book.getAuthor());
    }

}
