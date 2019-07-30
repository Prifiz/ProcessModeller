package entities.modeller;

import entities.Observable;
import entities.Observer;

import java.util.ArrayList;
import java.util.List;

public class ModelSystem implements Observable {

    private List<Observer> observers;
    // stores all the nodes common info (like snapshot)
    private String checksum;

    public boolean isAnythingChanged() {
        return false;
    }

    public void registerChanges() {
        // happens when something in processes changed (actions, process kills, etc. which influence process run logic)
        notifyObservers();
    }

    public ModelSystem() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::handleEvent);
    }
}
