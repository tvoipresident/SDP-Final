public class EBookDecorator implements BookDecorator {
    private Book book;

    public EBookDecorator(Book book) {
        this.book = book;
    }

    @Override
    public void displayInfo() {
        book.displayInfo();
        System.out.println(" - E-Book");
    }

    @Override
    public String getTitle() {
        return book.getTitle() + " - E-Book";
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
