package entities.system;

public interface Observable {
    void addObserver(Observer observer);
    void notifyAllObservers();
}
