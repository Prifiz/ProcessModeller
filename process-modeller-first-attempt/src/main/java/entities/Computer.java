package entities;

import lombok.Getter;

@Getter
public class Computer {

    private final String label;
    private HweImproved hwe;
    private OperatingSystem os;
    private NodeStatus nodeStatus = NodeStatus.OFF;

    // how many storages are supported?
    // can next storage be added?
    // etc.

    public Computer(String label) {
        this.label = label;
        this.hwe = new HweImproved();
        this.os = new OperatingSystem();
    }

    public void addProcess(AbstractProcess process) {
        os.registerProcess(process);
    }

    // maybe remove hwe as a separate object - no dedicated behavior!
    public void addStorage(StorageDevice storageDevice) {
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

    public void updateState() {
        // free space changed, etc.
    }
}
