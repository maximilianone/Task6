package task3.controller;

import org.apache.log4j.Logger;
import task3.model.Book;
import task3.views.MenuView;

import java.io.*;
import java.util.List;

public class FileController {
    private MenuView menuView;
    private final Logger logger = Logger.getLogger(FileController.class);

    public FileController(MenuView menuView) {
        this.menuView = menuView;
    }


    void saveToFile(String address, List<Book> library) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(address))) {
            for (Book book : library) {
                out.writeObject(book);
            }
            out.writeObject(1);
            logger.info("Saved to " + address);
        } catch (IOException e) {
            menuView.displayMessage(menuView.INVALID_PATH);
            logger.error(menuView.INVALID_PATH);
        }
    }

    List<Book> loadFile(String adress, List<Book> library) {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(adress))) {
                for(Object book;(book= in.readObject()) instanceof Book;) {
                    library.add((Book) book);
                }
                logger.info("File " + adress + " loaded");
            }
        } catch (IOException | ClassNotFoundException e) {
            menuView.displayMessage(menuView.FILE_NOT_FOUND);
            logger.error(menuView.FILE_NOT_FOUND);
        }
        return library;
    }
}
