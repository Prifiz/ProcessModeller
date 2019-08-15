package entities.processes.storages;

import entities.processes.AbstractProcess;
import entities.system.hwe.storages.StorageDevice;
import lombok.Getter;

@Getter
public class PeriodicDiskCleaner<S extends StorageDevice> extends AbstractProcess<S> {

    private long cleanupIntervalMillis;
    private long bytesToCleanup;

    private long currentTime = 0L;

    public PeriodicDiskCleaner(String processName) {
        super(processName);
    }

    public PeriodicDiskCleaner cleansMbytes(long mbytes) {
        this.bytesToCleanup = mbytesToBytes(mbytes);
        return this;
    }

    public PeriodicDiskCleaner oncePerDays(int days) {
        this.cleanupIntervalMillis = days * 24L * 3600L * 1000L;
        return this;
    }

    @Override
    public void useHweEntry(S hweEntry, long deltaTime) {
        currentTime += deltaTime;
        if (currentTime >= cleanupIntervalMillis) {
            hweEntry.cleanupSpace(bytesToCleanup);
            currentTime = 0L;
        }
    }
}
