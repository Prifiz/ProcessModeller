package entities.system;

import entities.system.hwe.HweImproved;
import entities.processes.OperatingSystem;
import entities.system.hwe.storages.StorageDevice;
import entities.processes.AbstractProcess;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Computer<S extends StorageDevice> implements Observable {

    private final String label;
    private HweImproved<S> hwe;
    private OperatingSystem<S> os;
    private NodeStatus nodeStatus = NodeStatus.OFF;

    private List<Observer> observers;

    // how many storages are supported?
    // can next storage be added?
    // etc.

    public Computer(String label) {
        this.label = label;
        this.hwe = new HweImproved<>();
        this.os = new OperatingSystem<>();
        this.observers = new ArrayList<>();
    }

    public void addProcess(AbstractProcess<S> process) {
        os.registerProcess(process);
    }

    // maybe remove hwe as a separate object - no dedicated behavior!
    public void addStorage(S storageDevice) {
        this.hwe.addStorage(storageDevice);
    }

    public boolean isOn() {
        return NodeStatus.ON.equals(nodeStatus);
    }

    public void turnOn() {
        this.nodeStatus = NodeStatus.ON;
    }

    public void turnOff() {
        this.nodeStatus = NodeStatus.OFF;
    }

    public void updateState(long currentTime) {
        // free space changed, etc.
        os.runAllProcesses(hwe, currentTime);
        notifyAllObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        observers.forEach(observer -> observer.react(this));
    }
}
