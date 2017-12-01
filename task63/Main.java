package task63;

import task63.controller.MainController;
import task63.input.MyScanner;
import task63.model.UnaryArithmeticOperation;
import task63.view.MainView;

public class Main {

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        MainView mainView = new MainView();
        MainController controller = new MainController(mainView,scanner);
        controller.run();
    }
}
