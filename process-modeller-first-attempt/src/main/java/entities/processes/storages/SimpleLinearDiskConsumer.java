package entities.processes.storages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleLinearDiskConsumer<T extends SimpleHdd> extends AbstractDiskProcess<T> {

    @JsonProperty
    private float diskConsumingSpeed; // bytes per millisecond



    @JsonProperty
    private long justConsumed = 0L;

    public SimpleLinearDiskConsumer(String processName) {
        super(processName, DiskUsageType.WRITE);
    }

    @Override
    public long getDiskUsageSpeed() {
        return 0;
    }

    @JsonCreator
    public SimpleLinearDiskConsumer(@JsonProperty("processName") String processName,
                                    @JsonProperty("processId") String processId,
                                    @JsonProperty("diskConsumingSpeed") float diskConsumingSpeed,
                                    @JsonProperty("justConsumed") long justConsumed) {
        super(processName, processId, DiskUsageType.WRITE);
        this.diskConsumingSpeed = diskConsumingSpeed;
        this.justConsumed = justConsumed;
    }

    public SimpleLinearDiskConsumer consumesMbPerDay(long mbytes) {
        this.diskConsumingSpeed = mbytesToBytes(mbytes) * 1.0f / (24 * 3600 * 1000);
        return this;
    }

    @Override
    public void useHweEntry(T hdd, long deltaTime) { // one process uses one hwe entry! todo create complex processes

        System.out.println("[Consume] Working with Hwe: " + hdd.getLabel());

        long writeSpeed = hdd.getWriteSpeed();
        long maxAbleToWrite = Math.round(writeSpeed * deltaTime);
        long spaceToConsume = Math.round(deltaTime * diskConsumingSpeed);

        long reallyConsumed = 0L;

        // simple check; write only as much as possible, data loss, no cache, no queues
        if (spaceToConsume <= maxAbleToWrite) {
            System.out.println("It's okay to consume data");
            reallyConsumed = spaceToConsume;
        } else {
            System.out.println("Couldn't consume the requested data, consuming max allowed by HDD");
            reallyConsumed = maxAbleToWrite;
        }

        hdd.consumeSpace(reallyConsumed);
        this.justConsumed = bytesToMb(reallyConsumed);
        notifyAllLoggers();

    }

//    @Override
//    public long getTimeLimit() {
//        return 0;
//    }

//    @Override
//    public boolean canBeRunInParallel() {
//        return false;
//    }
}
