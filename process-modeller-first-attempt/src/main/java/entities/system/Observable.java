package entities.system;

public interface Observable { // Loggable? Some concrete interface for logger attachment
    void addObserver(Observer observer);
    void notifyAllObservers();
}
