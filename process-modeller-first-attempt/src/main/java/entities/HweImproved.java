package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HweImproved<S extends StorageDevice> {

    protected List<S> storages;

    public HweImproved() {
        this.storages = new ArrayList<>();
    }

    public void addStorage(S storageDevice) {
        this.storages.add(storageDevice);
    }

    public void updateStorage(S device) {
        // some update logic here
    }

    public Optional<S> getFirstStorageByType(StorageType type) {
        return storages.stream()
                .filter(storageDevice -> type.equals(storageDevice.getStorageType()))
                .findFirst();
    }
}
