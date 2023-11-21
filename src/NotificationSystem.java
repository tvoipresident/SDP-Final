import java.util.ArrayList;
import java.util.List;

public class NotificationSystem {
    private List<Observer> observers;

    public NotificationSystem() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
