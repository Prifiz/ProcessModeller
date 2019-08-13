package entities.system.hwe;

import entities.system.hwe.storages.StorageDevice;

import java.util.List;

public class CustomHweUsagePolicy<S extends StorageDevice> implements HweUsagePolicy<S> {

    private List<HweEntry> entries;

    public CustomHweUsagePolicy(List<HweEntry> entries) {

        this.entries = entries;
    }

    @Override
    public List<S> getAllowedHdds(List<S> availableStorages) {
        return null;
    }
}
