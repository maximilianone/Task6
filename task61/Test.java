package task3;


import task3.controller.FileController;
import task3.input.MyScanner;
import task3.model.Book;
import task3.views.MenuView;
import task3.controller.Menu;
import static task3.model.LibraryCreator.createLibrary;


public class Test {
    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        MenuView menuView = new MenuView();
        FileController fileController = new FileController(menuView);
        Menu menu = new Menu(menuView, fileController, scanner);
        menu.run();
        scanner.closeScanner();
    }
}
