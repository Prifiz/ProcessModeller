package entities.processes;

import entities.system.hwe.HweImproved;
import entities.system.hwe.storages.StorageDevice;
import entities.system.hwe.storages.StorageType;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
public class OperatingSystem<S extends StorageDevice> implements ProcessManager<S> {


    private List<AbstractProcess<S>> processes; // needed mapping to hwe!!! // processpool?
    private long currentOsTime = 0L;

    public OperatingSystem() {
        processes = new LinkedList<>();
    }

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
