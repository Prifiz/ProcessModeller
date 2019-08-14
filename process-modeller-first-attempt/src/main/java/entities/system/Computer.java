package entities.system;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.processes.AbstractProcess;
import entities.processes.OperatingSystem;
import entities.system.hwe.HweImproved;
import entities.system.hwe.policies.HweUsagePolicy;
import entities.system.hwe.storages.StorageDevice;
import entities.system.logger.LoggedEntity;
import entities.system.logger.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Computer<S extends StorageDevice> implements LoggedEntity, ExportedEntity, ImportedEntity {
    // exported & imported can be retentions

    @JsonProperty(value = "label")
    private final String label;
    @JsonProperty
    private HweImproved<S> hwe;
    @JsonProperty
    private NodeStatus nodeStatus = NodeStatus.OFF;

    @JsonIgnore
    private List<Logger> loggers = new ArrayList<>();

    @JsonProperty
    private OperatingSystem<S> os;


    // how many storages are supported?
    // can next storage be added?
    // etc.
    public Computer(String label) {
        this.label = label;
        this.hwe = new HweImproved<>();
        this.os = new OperatingSystem<>();
//        this.loggers = new ArrayList<>();
    }

    @JsonCreator
    public Computer(@JsonProperty("label") String label,
                    @JsonProperty("hwe") HweImproved<S> hwe,
                    @JsonProperty("nodeStatus") NodeStatus nodeStatus,
                    @JsonProperty("os") OperatingSystem<S> os) {
        this.label = label;
        this.hwe = hwe;
        this.nodeStatus = nodeStatus;
        this.os = os;
    }

    // maybe remove hwe as a separate object - no dedicated behavior!
    public void addStorage(S storageDevice) {
        this.hwe.addStorage(storageDevice);
    }

    @JsonIgnore
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

    public void registerProcess(AbstractProcess<S> process, HweUsagePolicy<S> policy) {
        os.registerProcess(process, policy, hwe);
    }
}
