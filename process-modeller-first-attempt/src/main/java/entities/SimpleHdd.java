package entities;

public class SimpleHdd extends StorageDevice {

    public SimpleHdd(String label, long capacityGb) {
        super(label, StorageType.HDD, capacityGb);
    }

    public SimpleHdd canReadAt(long mbps) {
        this.readSpeed = mbps * 1024 * 1024;
        return this;
    }

    public SimpleHdd canWriteAt(long mbps) {
        this.writeSpeed = mbps * 1024 * 1024;
        return this;
    }
}
