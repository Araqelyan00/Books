import command.Menu;
import exception.AuthorNotFoundException;
import model.User;
import storage.AuthorStorage;
import storage.BookStorage;
import storage.UserStorage;

import java.util.Scanner;

public class Demo implements Menu {
    private final static AuthorStorage authorStorage = new AuthorStorage();
    private final static BookStorage bookStorage = new BookStorage();
    private final static UserStorage userStorage = new UserStorage();

    Scanner scanner = new Scanner(System.in);

    private static User registeredUser = null;
    static boolean runnable = true;

    public static void main(String[] args) throws AuthorNotFoundException {
        initdata();

        while (runnable) {
            Menu.printLoginCommands();

            int command;

            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    runnable = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        login();
        while (runnable) {
            Menu.printLoginCommands();
            try {
                int command = Integer.parseInt(scanner.nextLine().trim());
                int index;
                switch (command) {
                    case EXIT:
                        runnable = false;
                        break;
                    case ADD_BOOK:

                }
            } catch ()
        }

    }

}
