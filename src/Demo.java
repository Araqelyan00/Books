import command.Menu;
import exception.AuthorNotFoundException;
import model.Author;
import model.Book;
import model.Role;
import model.User;
import storage.AuthorStorage;
import storage.BookStorage;
import storage.UserStorage;

import java.util.Locale;
import java.util.Scanner;

public class Demo implements Menu {
    private final static AuthorStorage authorStorage = new AuthorStorage();
    private final static BookStorage bookStorage = new BookStorage();
    private final static UserStorage userStorage = new UserStorage();

    private final static Scanner scanner = new Scanner(System.in);

    private static User registeredUser = null;
    static boolean runnable = true;

    public static void main(String[] args) throws AuthorNotFoundException {
        initData();

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
            Menu.printUserCommands();
            try {
                int command = Integer.parseInt(scanner.nextLine().trim());
                int index;
                switch (command) {
                    case EXIT:
                        runnable = false;
                        break;
                    case ADD_BOOK:
                        addBook();
                        break;
                    case PRINT_ALL_BOOKS:
                        bookStorage.printAllBooks();
                        break;
                    case PRINT_BOOKS_BY_AUTHOR_NAME:
                        printBooksByAuthorName();
                        break;
                    case PRINT_BOOKS_BY_GENRE:
                        printBooksByGenre();
                        break;
                    case PRINT_BOOKS_BY_PRICE_RANGE:
                        printBooksByPriceRange();
                        break;
                    case ADD_AUTHOR:
                        addAuthor();
                        break;
                    case PRINT_ALL_AUTHORS:
                        authorStorage.printAuthors();
                        break;
                    case DELETE_AUTHOR_BY_INDEX:
                        authorStorage.printAuthors();
                        System.out.println("Input Authors Index :");
                        index = Integer.parseInt(scanner.nextLine());
                        authorStorage.deleteAuthorByIndex(index);
                        break;
                    case PRINT_AUTHOR_BY_INDEX:
                        System.out.println("Input Author Index :");
                        try {
                            index = Integer.parseInt(scanner.nextLine());
                            System.out.println(authorStorage.getAuthorByIndex(index));
                        } catch (AuthorNotFoundException e) {
                            System.out.println(e.getMessage() + "\n");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Can not do this function" + e.getMessage());
                        }
                        break;

                    default:
                        System.err.println("Command with index " + command + " does not exist.");

                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input Number!!");
            }
        }


    }

    private static void register() {
        System.out.println("Input your name, surname, email address and password with commas.");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Incorrect data");
        }
        if (userStorage.getUserByEmail(userData[2]) == null) {
            User user = new User();
            user.setName(userData[0].trim());
            user.setSurname(userData[1].trim());
            user.setEmail(userData[2].trim());
            user.setPassword(userData[3].trim());
            user.setRole(Role.USER);
            System.out.println("User has been created.");
            UserStorage.add(user);
        } else {
            System.out.println("User with email " + userData[2] + " already exists.");
        }
    }

    private static void initData() {
        Author Shield = new Author("Herbert", "Shield", "shield@gmail.com", "MALE", registeredUser);
        authorStorage.add(Shield);
        Author Sevak = new Author("Paruyr", "Sevak", "sevak@gmail.com", "MALE", registeredUser);
        authorStorage.add(Sevak);

        bookStorage.add(new Book("Java", Shield, 5000, 100, "Technical"));
        bookStorage.add(new Book("Sirum em qez", Sevak, 6000, 10, "Classical"));

        User admin = new User("Admin", "Adminyan", "admin@gmail.com", "admin", Role.ADMIN);
        userStorage.add(admin);
        User user = new User("User", "Useryan", "user@gmail.com", "user", Role.USER);
        userStorage.add(user);
    }

    static void login() throws AuthorNotFoundException {
        System.out.println("Input your email address and password with commas.");
        String emailPasswordStr = scanner.nextLine();

        String[] emailPassword = emailPasswordStr.split(",");

        User user = userStorage.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("User with email " + emailPassword[0] + " does not exist.");
        } else {
            if (user.getPassword().equals(emailPassword[1])) {
                registeredUser = user;
                if (user.getRole().equals(Role.ADMIN)) {
                    loginAdmin();
                } else if (user.getRole().equals(Role.USER)) {
                    loginUser();
                }
            }
        }
    }

    private static void loginUser() throws AuthorNotFoundException {
        boolean run = true;
        while (run) {
            Menu.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.printAllBooks();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printBooksByAuthorName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    System.out.println("Input the lowest price :");
                    double lowestPrice = scanner.nextDouble();
                    System.out.println("Input the highest price :");
                    double highestPrice = scanner.nextDouble();
                    bookStorage.printBooksByPriceRange(lowestPrice, highestPrice);
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.printAuthors();
                    break;
                case PRINT_AUTHOR_BY_INDEX:
                    authorStorage.printAuthors();
                    System.out.println("Input Author Index :");
                    int authorIndex = 0;
                    try{
                        authorIndex = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("Input numbers only !!");
                    }
                    authorStorage.getAuthorByIndex(authorIndex);
                    break;
            }
        }
    }

    private static void loginAdmin() throws AuthorNotFoundException {
        boolean run = true;
        while (run) {
            Menu.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.printAllBooks();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printBooksByAuthorName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    System.out.println("Input the lowest price :");
                    double lowestPrice = scanner.nextDouble();
                    System.out.println("Input the highest price :");
                    double highestPrice = scanner.nextDouble();
                    bookStorage.printBooksByPriceRange(lowestPrice, highestPrice);
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.printAuthors();
                    break;
                case DELETE_AUTHOR_BY_INDEX:
                    authorStorage.printAuthors();
                    System.out.println("Input Author Index :");
                    int authorIndex = 0;
                    try{
                        authorIndex = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("Input numbers only !!");
                    }
                    authorStorage.deleteAuthorByIndex(authorIndex);
                    break;
                case PRINT_AUTHOR_BY_INDEX:
                    authorStorage.printAuthors();
                    System.out.println("Input Author Index :");
                    int index = 0;
                    try{
                        index = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("Input numbers only !!");
                    }
                    authorStorage.getAuthorByIndex(index);
                    break;
                case PRINT_ALL_USERS:
                    printAllUsers();
//                    break;

            }
        }
    }
    private static void addBook() throws AuthorNotFoundException {
        try{
            System.out.println("Input book's title :");
            String bookName = scanner.nextLine();
            System.out.println("Input book's author index");
            int authorIndex = Integer.parseInt(scanner.nextLine());
            System.out.println("Input book's price :");
            double price = scanner.nextDouble();
            System.out.println("Input book's count :");
            int count = Integer.parseInt(scanner.nextLine());
            System.out.println("Input book's genre :");
            String genre = scanner.nextLine();

            Book book = new Book(bookName, authorStorage.getAuthorByIndex(authorIndex), price, count, genre);
            bookStorage.add(book);
            System.out.println("Book added successfully.");
        }catch (IllegalArgumentException e){
            System.out.println("Input numbers only !!");
        }
    }
    private static void addAuthor() throws AuthorNotFoundException {
        System.out.println("Input author's name :");
        String authorName = scanner.nextLine();

        System.out.println("Input author's surname :");
        String authorSurname = scanner.nextLine();

        System.out.println("Input author's email :");
        String email = scanner.nextLine();

        System.out.println("Input author's gender :");
        String gender = scanner.nextLine().toUpperCase(Locale.ROOT);

        Author author = new Author(authorName, authorSurname, email, gender, registeredUser);
        authorStorage.add(author);
        System.out.println("Author added successfully.");
    }

    private static void printBooksByAuthorName() {
        System.out.println("Input author's name :");
        String authorName = scanner.nextLine();
        bookStorage.printBooksByAuthorName(authorName.trim());
    }
    private static void printBooksByGenre() {
        System.out.println("Input genres :");
        String genres = scanner.nextLine();
        bookStorage.printBooksByGenre(genres.trim());
    }
    private static void printBooksByPriceRange() {
        try{
            System.out.println("Input lowest price :");
            double lowestPrice = Double.parseDouble(scanner.nextLine());
            System.out.println("Input highest price :");
            double highestPrice = Double.parseDouble(scanner.nextLine());
            bookStorage.printBooksByPriceRange(lowestPrice, highestPrice);
        }catch (IllegalArgumentException e){
            System.out.println("Input numbers only !!");
        }
    }
    public static void printAllUsers() {
        userStorage.printUsers();
    }
    public static void printAllBooks() {
        bookStorage.printAllBooks();
    }



}
