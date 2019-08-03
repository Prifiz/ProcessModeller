package entities.system.hwe.storages;

import entities.system.hwe.HweEntry;
import lombok.Getter;

@Getter
public class StorageDevice extends HweEntry {

    protected final String label;
    protected final StorageType storageType;

    protected long readSpeed; // bytes per second
    protected long writeSpeed; // bytes per second

    protected final long capacity; // bytes
    protected long usedSpace; // bytes


    public StorageDevice(String label, StorageType storageType, long capacityGb) {
        this.label = label;
        this.storageType = storageType;
        this.capacity = capacityGb * 1024 * 1024 * 1024;
    }
}
