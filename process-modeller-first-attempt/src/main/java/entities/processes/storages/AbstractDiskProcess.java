package entities.processes.storages;

import entities.processes.AbstractProcess;
import entities.system.hwe.storages.StorageDevice;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractDiskProcess<S extends StorageDevice> extends AbstractProcess<S> {

    @Getter
    protected final DiskUsageType diskUsageType;

    @Getter
    @Setter
    protected float requestedDiskUsageSpeed;

    @Getter
    @Setter
    protected float actualDiskUsageSpeed;

    public AbstractDiskProcess(String processName, DiskUsageType diskUsageType) {
        super(processName);
        this.diskUsageType = diskUsageType;
    }

    public AbstractDiskProcess(String processName, String processId, DiskUsageType diskUsageType) {
        super(processName, processId);
        this.diskUsageType = diskUsageType;
    }

    public abstract long getDiskUsageSpeed();

    public abstract long getBytesToUse(long deltaTime);

}
