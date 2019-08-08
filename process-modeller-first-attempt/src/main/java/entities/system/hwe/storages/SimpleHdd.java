package entities.system.hwe.storages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleHdd extends StorageDevice {

    public SimpleHdd(@JsonProperty String label, @JsonProperty long capacityGb) {
        super(label, StorageType.HDD, capacityGb);
    }

    public SimpleHdd canReadAt(long mbps) {
        this.readSpeed = mbps * 1024 * 1024;
        return this;
    }

    public SimpleHdd canWriteAt(long mbps) {
        this.writeSpeed = mbps * 1024 * 1024;
        return this;
    }
}
