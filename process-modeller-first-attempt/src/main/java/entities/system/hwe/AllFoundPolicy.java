package entities.system.hwe;

import entities.system.hwe.storages.StorageDevice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AllFoundPolicy<S extends StorageDevice> implements HweUsagePolicy<S> {

    public List<S> getAllowedHdds(List<S> availableStorages) {
        return availableStorages;
    }
}
