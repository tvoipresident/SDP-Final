import java.util.Scanner;

public class BookstoreMenu {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    private Admin admin;
    private BookFactory bookFactory;
    private NotificationSystem notificationSystem;

    public BookstoreMenu() {
        this.admin = new Admin();
        this.bookFactory = new BookFactory();
        this.notificationSystem = new NotificationSystem();
    }

    public void launchMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean isLoggedIn;

        do {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (name.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                // Admin login
                adminMenu(scanner);
            } else {
                // Customer login
                Customer customer = new Customer(name, notificationSystem);
                admin.addCustomer(customer);
                customerMenu(scanner, customer);
            }

            System.out.print("Do you want to log in again? (y/n): ");
            String response = scanner.nextLine();
            isLoggedIn = response.equalsIgnoreCase("y");
        } while (isLoggedIn);
    }

    private void adminMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Customer List");
            System.out.println("2. Delete Customer");
            System.out.println("3. Log Out");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.viewCustomerList();
                    break;
                case 2:
                    System.out.print("Enter customer index to delete: ");
                    int indexToDelete = scanner.nextInt();
                    scanner.nextLine();
                    admin.deleteCustomer(indexToDelete - 1);
                    break;
            }
        } while (choice != 3);
    }

    private void customerMenu(Scanner scanner, Customer customer) {
        int choice;

        do {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Books");
            System.out.println("2. Purchase Book");
            System.out.println("3. Log Out");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customer.viewBooks(bookFactory);
                    break;
                case 2:
                    purchaseBook(scanner, customer);
                    break;
            }
        } while (choice != 3);
    }


    private void purchaseBook(Scanner scanner, Customer customer) {
        Book selectedBook = chooseBook(scanner, bookFactory, customer);

        if (selectedBook != null) {
            System.out.println("Selected Book: " + selectedBook.getTitle());
            System.out.println("Author: " + selectedBook.getAuthor());
            System.out.println("Year: " + selectedBook.getYear());

            System.out.println("Choose a decoration:");
            System.out.println("1. E-Book");
            System.out.println("2. Physical Book");
            System.out.print("Enter your choice: ");
            int decorationChoice = scanner.nextInt();
            scanner.nextLine();

            BookDecorator decoratedBook = null;

            switch (decorationChoice) {
                case 1:
                    decoratedBook = new EBookDecorator(selectedBook);
                    break;
                case 2:
                    decoratedBook = new PhysicalBookDecorator(selectedBook);
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to no decoration.");
            }

            if (decoratedBook != null) {
                PaymentStrategy paymentStrategy = choosePaymentMethod(scanner);

                customer.purchaseBook(decoratedBook, paymentStrategy);
            }
        }
    }

    private Book chooseBook(Scanner scanner, BookFactory bookFactory, Customer customer) {
        customer.viewBooks(bookFactory);

        System.out.print("Choose a book type (fiction/nonfiction): ");
        String bookType = scanner.nextLine();

        System.out.print("Choose a book number: ");
        int bookChoice = scanner.nextInt();
        scanner.nextLine();

        Book selectedBook;

        switch (bookType.toLowerCase()) {
            case "fiction":
                selectedBook = bookFactory.getFictionBooks().get(bookChoice - 1);
                break;
            case "nonfiction":
                selectedBook = bookFactory.getNonFictionBooks().get(bookChoice - 1);
                break;
            default:
                System.out.println("Invalid book type.");
                return null;
        }

        System.out.println("Selected Book: " + selectedBook.getTitle());
        System.out.println("Author: " + selectedBook.getAuthor());
        System.out.println("Year: " + selectedBook.getYear());

        return selectedBook;
    }

    private PaymentStrategy choosePaymentMethod(Scanner scanner) {
        System.out.println("Choose a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Kaspi");
        System.out.print("Enter your choice: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        switch (paymentChoice) {
            case 1:
                return new CreditCardPayment();
            case 2:
                return new KaspiPayment();
            default:
                System.out.println("Invalid choice. Defaulting to Credit Card payment.");
                return new CreditCardPayment();
        }
    }

    public static void main(String[] args) {
        BookstoreMenu bookstoreMenu = new BookstoreMenu();
        bookstoreMenu.launchMenu();
    }
}