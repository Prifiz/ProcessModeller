package entities.processes;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.system.hwe.HweImproved;
import entities.system.hwe.HweUsagePolicy;
import entities.system.hwe.storages.StorageDevice;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperatingSystem<S extends StorageDevice> implements ProcessManager<S> {

    @JsonProperty
    //private List<AbstractProcess<S>> processes = new LinkedList<>(); // needed mapping to hwe!!! // processpool?

    private List<ProcessExecutor<S>> processes = new LinkedList<>();

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
            processes.add(new ProcessExecutor<>(process, Optional.of(hdd)));
        });
    }


    public void runAllProcesses(HweImproved<S> hwe, long currentSystemTime) {
        long deltaTime = currentSystemTime - currentOsTime;
        processes.forEach(process -> {
            process.execute(deltaTime);
        });
        currentOsTime = currentSystemTime;
    }
}
