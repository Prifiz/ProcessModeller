package entities.processes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.system.hwe.HweImproved;
import entities.system.hwe.storages.StorageDevice;
import entities.system.hwe.storages.StorageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
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
    private List<AbstractProcess<S>> processes = new LinkedList<>(); // needed mapping to hwe!!! // processpool?

    @JsonProperty
    private long currentOsTime = 0L;

//    @JsonCreator
//    public OperatingSystem(@JsonProperty("processes") AbstractProcess<S>[] processes, @JsonProperty("currentOsTime") long currentOsTime) {
//        this.processes = Arrays.asList(processes);
//        this.currentOsTime = currentOsTime;
//    }

    @Override
    public void registerProcess(AbstractProcess<S> process) {
//        HweUsagePolicy policy = process.getHweUsagePolicy();
//        policy.getAllowedHdds().forEach(hdd -> {
//            processes.add(process);
//        });
        processes.add(process);
    }


    public void runAllProcesses(HweImproved<S> hwe, long currentSystemTime) {
        long deltaTime = currentSystemTime - currentOsTime;
        processes.forEach(process -> {
            Optional<S> device = hwe.getFirstStorageByType(StorageType.HDD); // workaround simplification for prototype
            if(device.isPresent()) {
                S hdd = device.get();
                process.useHweEntry(hdd, deltaTime);
            }
        });
        currentOsTime = currentSystemTime;
    }
}
