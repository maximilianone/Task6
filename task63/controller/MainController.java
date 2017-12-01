package task63.controller;

import task63.input.MyScanner;
import task63.model.UnaryArithmeticOperation;
import task63.view.MainView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainController {
    private MainView mainView;
    private UnaryArithmeticOperation arithmeticOperation;
    private MyScanner scanner;
    private boolean isRunning = true;
    private Field value;
    private Method increment;
    private Method decrement;
    private Method change;

    public enum Menu {INCREMENT, DECREMENT, CHANGE, SHOW, EXIT, DEFAULT}

    private Class<?> clazz = UnaryArithmeticOperation.class;

    public MainController(MainView mainView, MyScanner scanner) {
        this.mainView = mainView;
        this.scanner = scanner;
    }

    public void run() {
        createUAO();
        initiate();
        while (isRunning) {
            mainView.showMenu();
            chooseOption(scanner.readInput());
        }
    }

    private void createUAO() {
        boolean invalidInput = true;
        double value;
        while (invalidInput) {
            mainView.displayMessage(mainView.REQUEST_INIT_VALUE);
            try {
                value = Double.parseDouble(scanner.readInput());
                try {
                    Constructor<?> constructor = clazz.getConstructor(double.class);
                    try {
                        arithmeticOperation = (UnaryArithmeticOperation) constructor.newInstance(value);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                mainView.displayMessage(mainView.RESULT + arithmeticOperation.getValue());
                invalidInput = false;
            } catch (NumberFormatException e) {
                mainView.displayMessage(mainView.WRONG_INPUT);
            }
        }
    }

    private void initiate(){
        try {
            value = clazz.getDeclaredField("value");
            value.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            increment = clazz.getMethod("increment");
            decrement = clazz.getMethod("decrement");
            change  = clazz.getMethod("changeSign");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void chooseOption(String statement) {
        Menu option;
        try {
            option = Menu.valueOf(statement.toUpperCase());
        } catch (java.lang.IllegalArgumentException e) {
            option = Menu.DEFAULT;
        }
        switch (option) {
            case INCREMENT:
                operation(increment);
                break;
            case DECREMENT:
                operation(decrement);
                break;
            case CHANGE:
                operation(change);
                break;
            case SHOW:
                displayValue();
            case EXIT:
                exit();
                break;
            case DEFAULT:
                mainView.displayMessage(mainView.WRONG_INPUT);
                break;
        }
    }

    private void operation(Method method) {
            try {
                method.invoke(arithmeticOperation);
                displayValue();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
    }

    private void displayValue() {
            try {
                System.out.println(mainView.RESULT + value.getDouble(arithmeticOperation));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
    }

    private void exit() {
        isRunning = false;
    }


}
