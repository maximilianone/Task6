package task62;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringModifier modifier = new StringModifier();
        modifier.changeExplicit();
        System.out.println("Enter string to change and then resulting string: ");
        modifier.changeImplicit(in.nextLine(),in.nextLine());
    }
}
