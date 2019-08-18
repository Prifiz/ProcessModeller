package entities.processes;

import entities.system.hwe.HweImproved;
import entities.system.hwe.policies.HweUsagePolicy;
import entities.system.hwe.storages.StorageDevice;

public class StorageProcessManager<S extends StorageDevice> implements ProcessManager<S> {

    @Override
    public void registerProcess(AbstractProcess<S> process, HweUsagePolicy<S> policy, HweImproved<S> hweImproved) {

    }
}
