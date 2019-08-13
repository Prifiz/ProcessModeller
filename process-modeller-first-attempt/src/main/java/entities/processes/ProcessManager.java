package entities.processes;

import entities.system.hwe.HweImproved;
import entities.system.hwe.HweUsagePolicy;
import entities.system.hwe.storages.StorageDevice;

// TODO sometimes it's worth specifying memory for a process group
//  rather that for each process to avoid accumulative errors
public interface ProcessManager<S extends StorageDevice> {
    void registerProcess(AbstractProcess<S> process, HweUsagePolicy<S> policy, HweImproved<S> hweImproved);
}
