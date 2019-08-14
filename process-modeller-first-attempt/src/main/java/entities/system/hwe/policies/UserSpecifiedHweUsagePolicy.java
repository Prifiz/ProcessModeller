package entities.system.hwe.policies;

import entities.system.hwe.storages.StorageDevice;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifiedHweUsagePolicy<S extends StorageDevice> implements HweUsagePolicy<S> {

    private List<S> storages;

    public UserSpecifiedHweUsagePolicy(List<S> storages) {
        this.storages = storages;
    }

    @Override
    public List<S> getAllowedHdds(List<S> availableStorages) {
        List<S> result = new ArrayList<>();
        storages.forEach( storage -> {
            if (availableStorages.contains(storage)) {
                result.add(storage);
            } else {
                System.out.println(
                        String.format(
                                "Requested storage [%s] not found among available ones",
                                storage.getLabel()));
            }
        });
        return result;
    }
}
