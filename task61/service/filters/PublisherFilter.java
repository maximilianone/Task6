package task3.service.filters;

import task3.model.Book;

public class PublisherFilter extends Filter {
    private final String publisher;

    public PublisherFilter(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean checkCondition(Book book) {
        return publisher.equals(book.getPublisher());
    }
}
