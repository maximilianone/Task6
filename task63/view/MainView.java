package task63.view;

public class MainView implements Constants{
    public void showMenu(){
        System.out.println(SHOW);
        System.out.println(INCREMENT_OPRION);
        System.out.println(DECREMENT_OPTION);
        System.out.println(CHANGE_SIGN_OPTION);
        System.out.println(EXIT_OPTION);
    }

    public void displayMessage(String message){
        System.out.println(message);
    }
}
