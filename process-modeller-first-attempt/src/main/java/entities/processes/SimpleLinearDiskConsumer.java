package entities.processes;

import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

@Getter
public class SimpleLinearDiskConsumer<T extends SimpleHdd> extends AbstractProcess<T> {

    private float diskConsumingSpeed; // bytes per millisecond

    private long justConsumed = 0L;

    public SimpleLinearDiskConsumer(String processName) {
        super(processName);
    }

    public SimpleLinearDiskConsumer consumesMbPerDay(long mbytes) {
        this.diskConsumingSpeed = mbytesToBytes(mbytes) * 1.0f / (24 * 3600 * 1000);
        return this;
    }

    private long mbytesToBytes(long mbytes) {
        return mbytes * 1024 * 1024;
    } // fixme replace with javax.metrics

    private long bytesToMb(long bytes) {
        return  bytes / 1024 / 1024;
    }

    @Override
    public void useHweEntry(T hdd, long deltaTime) { // one process uses one hwe entry! todo create complex processes
        long spaceToConsume = Math.round(deltaTime * diskConsumingSpeed);
        hdd.consumeSpace(spaceToConsume);
        this.justConsumed = bytesToMb(spaceToConsume);
        notifyAllLoggers();
    }

//    @Override
//    public boolean canBeRunInParallel() {
//        return false;
//    }
}
