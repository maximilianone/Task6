package task62;

import java.lang.reflect.Field;

class StringModifier {
    private Class<?> clazz = String.class;

    void changeExplicit() {
        String example1 = "unmodified";
        System.out.println("unmodified");
        try {
            Field example = clazz.getDeclaredField("value");
            example.setAccessible(true);
            example.set(example1, "modified".getBytes());
            System.out.println("unmodified");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void changeImplicit(String stringToChange, String resultingString) {
        System.out.println(stringToChange);
        try {
            Field example = clazz.getDeclaredField("value");
            example.setAccessible(true);
            example.set(stringToChange, resultingString.getBytes());
            System.out.println(stringToChange);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
