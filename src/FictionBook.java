public class FictionBook implements Book {
    private String title;
    private String author;
    private int year;

    public FictionBook(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public void displayInfo() {
        System.out.println("Fiction Book: " + getTitle() + " by " + getAuthor() + " (" + getYear() + ")");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public int getYear() {
        return year;
    }
}