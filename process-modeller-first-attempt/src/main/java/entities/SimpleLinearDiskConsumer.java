package entities;

import lombok.Getter;

@Getter
public class SimpleLinearDiskConsumer<T extends SimpleHdd> extends AbstractProcess<T> {

    private String processName;
    private long diskConsumingSpeed; // bytes per second

    public SimpleLinearDiskConsumer(String processName) {
        this.processName = processName;
    }

    public SimpleLinearDiskConsumer consumesMbPerDay(long mbytes) {
        this.diskConsumingSpeed = mbytesToBytes(mbytes) / 3600;
        return this;
    }

    private long mbytesToBytes(long mbytes) {
        return mbytes * 1024 * 1024;
    }


    @Override
    public T useHweEntry(T hdd, long deltaTime) { // one process uses one hwe entry! todo create complex processes
        T result = hdd;


        return result;
    }

    @Override
    public boolean canBeRunInParallel() {
        return false;
    }
}
