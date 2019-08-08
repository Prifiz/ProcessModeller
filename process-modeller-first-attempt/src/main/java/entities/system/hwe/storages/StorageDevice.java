package entities.system.hwe.storages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.system.hwe.HweEntry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StorageDevice extends HweEntry {

    @JsonProperty
    protected final String label;
    @JsonProperty
    protected final StorageType storageType;

    @JsonProperty
    protected long readSpeed; // bytes per second
    @JsonProperty
    protected long writeSpeed; // bytes per second
    @JsonProperty
    protected final long capacity; // bytes
    @JsonProperty
    protected long usedSpace; // bytes

    public StorageDevice(
            @JsonProperty String label,
            @JsonProperty StorageType storageType,
            @JsonProperty long capacityGb) {
        this.label = label;
        this.storageType = storageType;
        this.capacity = capacityGb * 1024 * 1024 * 1024;
    }

    @JsonCreator
    public StorageDevice(
            @JsonProperty("label") String label,
            @JsonProperty("storageType") StorageType storageType,
            @JsonProperty("capacityGb") long capacityGb,
            @JsonProperty("readSpeed") long readSpeed,
            @JsonProperty("writeSpeed") long writeSpeed,
            @JsonProperty("usedSpace") long usedSpace) {
        this(label, storageType, capacityGb);
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
        this.usedSpace = usedSpace;
    }

    public void consumeSpace(long bytes) {
        usedSpace += bytes;
    }
}
