package entities.processes.storages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.processes.AbstractProcess;
import entities.system.hwe.policies.HweUsagePolicy;
import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleLinearDiskConsumer<T extends SimpleHdd> extends AbstractProcess<T> {

    @JsonProperty
    private float diskConsumingSpeed; // bytes per millisecond

    @JsonProperty
    private long justConsumed = 0L;

    public SimpleLinearDiskConsumer(String processName) {
        super(processName);
    }

    @JsonCreator
    public SimpleLinearDiskConsumer(@JsonProperty("processName") String processName,
                                    @JsonProperty("processId") String processId,
                                    @JsonProperty("diskConsumingSpeed") float diskConsumingSpeed,
                                    @JsonProperty("justConsumed") long justConsumed) {
        super(processName, processId);
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
