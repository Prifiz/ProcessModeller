package entities.system.hwe;

import entities.system.hwe.storages.StorageDevice;

import java.util.ArrayList;
import java.util.List;

public class HweUsagePolicy {

    public List<StorageDevice> getAllowedHdds() {
        return new ArrayList<>();
    }
    // default is first found
}
