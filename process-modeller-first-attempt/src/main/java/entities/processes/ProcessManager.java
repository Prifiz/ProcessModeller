package entities.processes;

import entities.system.hwe.storages.StorageDevice;

public interface ProcessManager<S extends StorageDevice> {
    void registerProcess(AbstractProcess<S> process);
}
