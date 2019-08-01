package entities;

import lombok.Getter;

@Getter
public class StorageDevice extends HweEntry {

    protected final String label;

    protected long readSpeed; // bytes per second
    protected long writeSpeed; // bytes per second

    protected final long capacity; // bytes
    protected long usedSpace; // bytes


    public StorageDevice(String label, long capacityGb) {
        this.label = label;
        this.capacity = capacityGb * 1024 * 1024 * 1024;
    }
}
