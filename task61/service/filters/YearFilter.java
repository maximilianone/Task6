package task3.service.filters;

import task3.model.Book;

public class YearFilter extends Filter {
    private final int year;

    public YearFilter(int year) {
        this.year = year;
    }

    @Override
    public boolean checkCondition(Book book) {
        return year<=book.getYear();
    }
}
