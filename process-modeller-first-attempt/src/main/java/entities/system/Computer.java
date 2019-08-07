package entities.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.processes.AbstractProcess;
import entities.processes.OperatingSystem;
import entities.system.hwe.HweImproved;
import entities.system.hwe.storages.StorageDevice;
import entities.system.logger.LoggedEntity;
import entities.system.logger.Logger;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Computer<S extends StorageDevice> implements LoggedEntity {

    private final String label;
    private HweImproved<S> hwe;

    @JsonIgnore
    private List<Logger> loggers;
    @JsonIgnore
    private OperatingSystem<S> os;
    @JsonIgnore
    private NodeStatus nodeStatus = NodeStatus.OFF;

    // how many storages are supported?
    // can next storage be added?
    // etc.

    public Computer(String label) {
        this.label = label;
        this.hwe = new HweImproved<>();
        this.os = new OperatingSystem<>();
        this.loggers = new ArrayList<>();
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

    public void updateState(long currentSystemTime) {
        // free space changed, etc.
        os.runAllProcesses(hwe, currentSystemTime);
        notifyAllLoggers();
    }

    // TODO detach also needed

    @Override
    public void attachLogger(Logger logger) {
        this.loggers.add(logger);
    }

    @Override
    public void notifyAllLoggers() {
        loggers.forEach(Logger::doLog);
    }
}
