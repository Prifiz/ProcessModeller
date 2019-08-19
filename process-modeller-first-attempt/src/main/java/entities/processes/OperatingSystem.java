package entities.processes;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.system.hwe.HweImproved;
import entities.system.hwe.policies.HweUsagePolicy;
import entities.system.hwe.storages.StorageDevice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperatingSystem<S extends StorageDevice> implements ProcessManager<S> {
// separate OS entities for different HWE types? HDD OS, SSD OS, RAM OS?..
    @JsonProperty
    //private List<AbstractProcess<S>> processes = new LinkedList<>(); // needed mapping to hwe!!! // processpool?

    private List<ProcessExecutor<S>> processExecutors = new LinkedList<>();

    // what happens if hwe removed? unregister the linked processes!

    @JsonProperty
    private long currentOsTime = 0L;

//    @JsonCreator
//    public OperatingSystem(@JsonProperty("processes") AbstractProcess<S>[] processes, @JsonProperty("currentOsTime") long currentOsTime) {
//        this.processes = Arrays.asList(processes);
//        this.currentOsTime = currentOsTime;
//    }

    @Override
    public void registerProcess(AbstractProcess<S> process, HweUsagePolicy<S> policy, HweImproved<S> hweImproved) {
        List<S> availableStorages = hweImproved.getStorages();
        policy.getAllowedHdds(availableStorages).forEach(hdd -> {
            try {
                ProcessExecutor<S> processExecutor = new ProcessExecutor<>(process.clone(), Optional.of(hdd));
                //processExecutor.attachLogger(new ProcessExecutorConsoleLogger(processExecutor));
                processExecutors.add(processExecutor);
            } catch (CloneNotSupportedException ex) {
                System.out.println("Couldn't clone process");
                System.out.println(ex.getMessage());
            }
        });
    }

    public void updatePolicies() { // e.g. for most-free hdd policy, i.e. recalculate free hdd

    }

    private void checkProcesses(long deltaTime) {
        processExecutors.forEach(executor -> {

        });
    }

    // disk write speed for processes should be checked here, since
    // several concurrent writes can change the check conditions


    public void runAllProcesses(long currentSystemTime) {
        long deltaTime = currentSystemTime - currentOsTime;
        System.out.println("Delta time for all " + deltaTime / 1000 / 3600 / 24);
        processExecutors.forEach(processExecutor -> {
            processExecutor.execute(deltaTime);
            processExecutor.notifyAllLoggers();
        });
        currentOsTime = currentSystemTime;
    }
}
