package task3.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryCreator {
     public static List<Book> createLibrary() {
        String[] name = {"The lord of the rings", "The lord of the rings", "Bot", "Hobbit", "1984", "The Plague",
                "The Count of Monte Cristo"};
        String[] author = {"Tolkien", "Tolkien", "Kidruk", "Tolkien", "Orwell", "Camus", "Dumas"};
        String[] publisher = {"Bro", "Azpublic", "Family Club", "Bro", "Azpublic", "Bro", "Bro"};
        int[] year = {1987, 2000, 2014, 1980, 1950, 1970, 1989};
        int[] pageNumber = {1240, 1020, 620, 300, 326, 412, 637};
        double[] price = {10, 25, 4.24, 5, 11.30, 3.56, 6.55};
        List<Book> library = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            library.add(new Book(name[i], author[i], publisher[i], year[i], pageNumber[i], price[i]));
        }
        return library;
    }
}
