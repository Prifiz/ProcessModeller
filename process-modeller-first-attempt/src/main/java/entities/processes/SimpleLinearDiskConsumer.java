package entities.processes;

import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

@Getter
public class SimpleLinearDiskConsumer<T extends SimpleHdd> extends AbstractProcess<T> {

    private String processName;
    private float diskConsumingSpeed; // bytes per millisecond

    public SimpleLinearDiskConsumer(String processName) {
        this.processName = processName;
    }

    public SimpleLinearDiskConsumer consumesMbPerDay(long mbytes) {
        this.diskConsumingSpeed = mbytesToBytes(mbytes) / (24 * 3600 * 1000);
        return this;
    }

    private long mbytesToBytes(long mbytes) {
        return mbytes * 1024 * 1024;
    } // fixme replace with javax.metrics


    @Override
    public T useHweEntry(T hdd, long deltaTime) { // one process uses one hwe entry! todo create complex processes
        T result = hdd;
        result.consumeSpace(Math.round(deltaTime * diskConsumingSpeed));
        return result;
    }

//    @Override
//    public boolean canBeRunInParallel() {
//        return false;
//    }
}
