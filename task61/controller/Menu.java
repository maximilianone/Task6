package task3.controller;


import org.apache.log4j.Logger;
import task3.model.Book;
import task3.input.MyScanner;
import task3.model.LibraryCreator;
import task3.service.filters.AuthorFilter;
import task3.service.filters.PublisherFilter;
import task3.service.filters.YearFilter;
import task3.views.MenuView;
import task3.service.comparators.BookPublisherComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu implements Patterns{
    private final Logger logger = Logger.getLogger(Menu.class);

    private List<Book> library = new ArrayList<>();
    private MenuView menuView;
    private MyScanner scanner;
    private FileController fileController;
    private BookPublisherComparator comparator;
    private boolean menu;
    private enum MenuOption{SEARCH_BY_AUTHOR, SEARCH_BY_PUBLISHER, SEARCH_BY_YEAR,
        SORT, ADD_BOOK, SHOW_ALL, SAVE, EXIT, DEFAULT}
    private enum FileOption{CREATE, LOAD}

    public Menu(MenuView menuView, FileController fileController, MyScanner scanner) {
        this.menuView = menuView;
        this.fileController = fileController;
        this.scanner = scanner;
        this.comparator = new BookPublisherComparator();
    }

    public void run() {
        menu = true;
        menuView.showStart();
//        library = LibraryCreator.createLibrary();
        processFileOption(inputValue(menuView.COMMAND,START_PATTERN));
        while (menu) {
            menuView.menuText();
            processInput(scanner.readInput());
        }
    }

    private void processFileOption(String statement) {
        FileOption fileOption;
        fileOption = FileOption.values()[(Integer.parseInt(statement)) - 1];
        switch (fileOption) {
            case CREATE:
                break;
            case LOAD:
                loadFile();
                break;
        }
    }

    private void processInput(String statement) {
        MenuOption option;
        try{
            option = MenuOption.values()[(Integer.parseInt(statement))-1];
        }catch (NumberFormatException|IndexOutOfBoundsException e){
            option = MenuOption.DEFAULT;
        }
        switch (option) {
            case SEARCH_BY_AUTHOR:
                searchByAuthor();
                break;
            case SEARCH_BY_PUBLISHER:
                searchByPublisher();
                break;
            case SEARCH_BY_YEAR:
                searchByYear();
                break;
            case SORT:
                sortByPublisher();
                break;
            case ADD_BOOK:
                addBook();
                break;
            case SHOW_ALL:
                showAll();
                break;
            case SAVE:
                saveLibrary();
                break;
            case EXIT:
                exit();
                break;
            case DEFAULT:
                menuView.displayMessage(menuView.WRONG_INPUT);
                break;
        }
    }

    private void searchByAuthor() {
        menuView.displayMessage(menuView.REQUEST_AUTHOR);
        String author = scanner.readInput();
        List<Book> result = new AuthorFilter(author).filter(library);
        logger.info("Result of searching books of " + author+ ":" + result);
        menuView.display(menuView.NO_AUTHOR, result);

    }

    private void searchByPublisher() {
        menuView.displayMessage(menuView.REQUEST_PUBLISHER);
        String publisher = scanner.readInput();
        List<Book> result = new PublisherFilter(publisher).filter(library);
        logger.info("Result of searching books of publisher " + publisher+ ":"+result);
        menuView.display(menuView.NO_PUBLISHER, result);
    }

    private void searchByYear() {
        int year = Integer.parseInt(inputValue(menuView.REQUEST_YEAR,YEAR_PATTERN));
        List<Book> result = new YearFilter(year).filter(library);
        logger.info("Result of searching books after year " + year+ ":" + result);
        menuView.display(menuView.NO_AFTER_YEAR, result);
    }

    @SuppressWarnings("unchecked")
    private void sortByPublisher() {
        library.sort(comparator);
        logger.info("Library sorted by publisher");
        menuView.displayMessage(menuView.LIBRARY_SORTED);
    }

    private void addBook(){
        Book book = new Book();
        String name = inputValue(menuView.REQUEST_NAME, PATTERN);
        book.setName(name);
        String author = inputValue(menuView.REQUEST_AUTHOR, PATTERN);
        book.setAuthor(author);
        String publisher = inputValue(menuView.REQUEST_PUBLISHER, PATTERN);
        book.setPublisher(publisher);
        String year = inputValue(menuView.REQUEST_YEAR, YEAR_PATTERN);
        book.setYear(Integer.parseInt(year));
        String page = inputValue(menuView.REQUEST_PAGE, PAGE_PATTERN);
        book.setPageNumber(Integer.parseInt(page));
        String price = inputValue(menuView.REQUEST_PRICE, PRICE_PATTERN);
        book.setPrice(Double.parseDouble(price));
        library.add(book);
        logger.info("Book added: " + book);
    }

    private void saveLibrary(){
        String address = inputValue(menuView.REQUEST_SAVE_FILE,PATTERN);
        fileController.saveToFile(address,library);
    }

    private void loadFile(){
        String address = inputValue(menuView.REQUEST_LOAD_FILE,PATTERN);
        library = fileController.loadFile(address, library);
    }

    private String inputValue(String request, String patt){
        while(true){
            menuView.displayMessage(request);
            String input = scanner.readInput();
            Pattern pattern = Pattern.compile(patt);
            Matcher m = pattern.matcher(input);
            if (m.find()){
                return input;
            }
            menuView.displayMessage((MenuView.WRONG_INPUT));
        }
    }

    private void showAll() {
        menuView.display(menuView.NO_LIBRARY, library);
    }

    private void exit() {
        menu = false;
    }
}




