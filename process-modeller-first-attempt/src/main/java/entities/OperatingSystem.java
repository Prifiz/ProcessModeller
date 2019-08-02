package entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OperatingSystem<S extends StorageDevice> implements ProcessManager<S> {

    private List<AbstractProcess<S>> processes; // needed mapping to hwe!!!
    private long previousTime = 0L;

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


    public void runAllProcesses(HweImproved<S> hwe, long currentTime) {
        long deltaTime = currentTime - previousTime;
        processes.forEach(process -> {
            Optional<S> device = hwe.getFirstStorageByType(StorageType.HDD); // workaround simplification for prototype
            device.ifPresent(storageDevice -> hwe.updateStorage(process.useHweEntry(storageDevice, deltaTime)));
        });
        previousTime = currentTime;
    }
}
