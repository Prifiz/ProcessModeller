package entities.system.hwe;

import com.fasterxml.jackson.annotation.JsonCreator;
import entities.system.hwe.storages.StorageDevice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class HweUsagePolicy {

    @JsonCreator
    public HweUsagePolicy() {
    }

    public List<StorageDevice> getAllowedHdds() {
        return new ArrayList<>();
    }
    // default is first found
}
