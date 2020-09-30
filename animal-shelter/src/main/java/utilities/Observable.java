package utilities;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Observable {

    protected List<Observer> observers;

    public Observable() {
        observers = new CopyOnWriteArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (var observer : observers) {
            observer.update();
        }
    }
}
