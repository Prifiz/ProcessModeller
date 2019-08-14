package entities.system.hwe.policies;

import entities.system.hwe.storages.StorageDevice;

import java.util.List;

public interface HweUsagePolicy<S extends StorageDevice> {
    //@JsonCreator

    List<S> getAllowedHdds(List<S> availableStorages);
    // default is first found
}
