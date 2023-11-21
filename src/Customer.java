public class Customer implements Observer {
    private String name;
    private NotificationSystem notificationSystem;

    public Customer(String name, NotificationSystem notificationSystem) {
        this.name = name;
        this.notificationSystem = notificationSystem;
        this.notificationSystem.addObserver(this);
    }

    public String getName() {
        return name;
    }

    public void viewBooks(BookFactory bookFactory) {
        System.out.println("Available Books:");
        System.out.println("Fiction Books:");
        for (int i = 0; i < bookFactory.getFictionBooks().size(); i++) {
            System.out.println((i + 1) + ". " + bookFactory.getFictionBooks().get(i).getTitle());
        }

        System.out.println("Non-fiction Books:");
        for (int i = 0; i < bookFactory.getNonFictionBooks().size(); i++) {
            System.out.println((i + 1) + ". " + bookFactory.getNonFictionBooks().get(i).getTitle());
        }

    }

    public void purchaseBook(BookDecorator decoratedBook, PaymentStrategy paymentStrategy) {
        if (decoratedBook == null) {
            System.out.println("No book selected for purchase.");
            return;
        }

        System.out.println("Purchasing Book:");
        decoratedBook.displayInfo();
        paymentStrategy.pay(1500.0);
        if (decoratedBook instanceof EBookDecorator) {
            notificationSystem.notifyObservers("Your e-book has been sent to your email.");
        } else if (decoratedBook instanceof PhysicalBookDecorator) {
            notificationSystem.notifyObservers("Your order has been placed.");
            notificationSystem.notifyObservers("Your book is on its way.");
            notificationSystem.notifyObservers("Your book has been delivered.");
        }
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}
