package entities.system.hwe;

import entities.system.hwe.storages.StorageDevice;
import entities.system.hwe.storages.StorageType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class HweImproved<S extends StorageDevice> {

    protected List<S> storages;

    public HweImproved() {
        this.storages = new ArrayList<>();
    }

    public void addStorage(S storageDevice) {
        this.storages.add(storageDevice);
    }

    public Optional<S> getFirstStorageByType(StorageType type) {
        return storages.stream()
                .filter(storageDevice -> type.equals(storageDevice.getStorageType()))
                .findFirst();
    }
}
