package task3.views;

import org.apache.log4j.Logger;
import task3.controller.Menu;
import task3.model.Book;

import java.util.List;

public class MenuView implements MenuConstants{
    private final Logger logger = Logger.getLogger(MenuView.class);

    public void menuText() {
        System.out.println("Enter 1 to find books of needed author");
        System.out.println("Enter 2 to find books of needed publisher");
        System.out.println("Enter 3 to find books published after specified year");
        System.out.println("Enter 4 to sort books by publisher");
        System.out.println("Enter 5 to add book to library");
        System.out.println("Enter 6 to show all books in library");
        System.out.println("Enter 7 to save library");
        System.out.println("Enter 8 to exit");
    }

    public void showStart(){
        System.out.println("Enter 1 to create new file");
        System.out.println("Enter 2 to load file");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void display(String nullResult, List<Book> result) {
        if (result.size() == 0) {
            displayMessage(nullResult);
            logger.info(nullResult);
        } else {
            new LibraryView().showLibrary(result);
        }
    }
}

