import java.util.ArrayList;
import java.util.List;

public class BookFactory {
    private List<Book> fictionBooks;
    private List<Book> nonFictionBooks;

    public BookFactory() {
        initializeFictionBooks();
        initializeNonFictionBooks();
    }

    private void initializeFictionBooks() {
        fictionBooks = new ArrayList<>();
        fictionBooks.add(new FictionBook("The Hunger Games", "Suzanne Collins", 2008));
        fictionBooks.add(new FictionBook("Fahrenheit 451", "Ray Bradbury", 1953));
        fictionBooks.add(new FictionBook("The Hobbit", "J.R.R. Tolkien", 1937));
    }

    private void initializeNonFictionBooks() {
        nonFictionBooks = new ArrayList<>();
        nonFictionBooks.add(new NonFictionBook("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 1994));
        nonFictionBooks.add(new NonFictionBook("Abai Zholy", "Mukhtar Auezov", 1942));
    }

    public List<Book> getFictionBooks() {
        return fictionBooks;
    }

    public List<Book> getNonFictionBooks() {
        return nonFictionBooks;
    }

}
