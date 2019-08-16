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
        this.currentTime += deltaTime;
        System.out.println("[Cleanup] Working with Hwe: " + hweEntry.getLabel());
        System.out.println("[Cleanup] Current time: " + currentTime / 1000 / 3600 / 24);
        if (this.currentTime >= cleanupIntervalMillis) {
            System.out.println("[Cleanup] It's time to cleanup");
            hweEntry.cleanupSpace(bytesToCleanup);
            this.currentTime = 0L;
            System.out.println("[Cleanup] Current time after cleanup: " + currentTime / 1000 / 3600 / 24);
        }
    }
}
