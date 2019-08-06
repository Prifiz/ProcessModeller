package entities.processes;

import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

@Getter
public class SimpleLinearDiskConsumer<T extends SimpleHdd> extends AbstractProcess<T> {

    private float diskConsumingSpeed; // bytes per millisecond

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

    @Override
    public void useHweEntry(T hdd, long deltaTime) { // one process uses one hwe entry! todo create complex processes
        hdd.consumeSpace(Math.round(deltaTime * diskConsumingSpeed));
    }

//    @Override
//    public boolean canBeRunInParallel() {
//        return false;
//    }
}
