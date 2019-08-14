package entities.system.hwe.policies;

import entities.system.hwe.storages.StorageDevice;

import java.util.ArrayList;
import java.util.List;

public class LabelBasedPolicy<S extends StorageDevice> implements HweUsagePolicy<S> {

    private final String label;

    public LabelBasedPolicy(String label) {
        this.label = label;
    }

    @Override
    public List<S> getAllowedHdds(List<S> availableStorages) {
        List<S> result = new ArrayList<>();
        availableStorages.forEach( storage -> {
            if (storage.getLabel().matches(label)) {
                result.add(storage);
            } else {
                System.out.println(
                        String.format(
                                "Storage with label regex [%s] not matches storage [%s]",
                                label,
                                storage.getLabel()));
            }
        });
        return result;
    }
}
