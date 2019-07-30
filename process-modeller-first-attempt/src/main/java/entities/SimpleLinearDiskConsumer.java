package entities;

import lombok.Getter;

@Getter
public class SimpleLinearDiskConsumer extends AbstractProcess {

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
    public boolean canBeRunInParallel() {
        return false;
    }
}
