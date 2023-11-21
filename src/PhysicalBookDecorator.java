public class PhysicalBookDecorator implements BookDecorator {
    private Book book;

    public PhysicalBookDecorator(Book book) {
        this.book = book;
    }

    @Override
    public void displayInfo() {
        book.displayInfo();
        System.out.println(" - Physical Book");
    }

    @Override
    public String getTitle() {
        return book.getTitle() + " - Physical Book";
    }

    @Override
    public String getAuthor() {
        return book.getAuthor();
    }

    @Override
    public int getYear() {
        return book.getYear();
    }
}
