package entities.system.hwe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import entities.system.hwe.storages.StorageDevice;
import entities.system.hwe.storages.StorageType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
public class HweImproved<S extends StorageDevice> {



    @JsonProperty
    protected List<S> storages = new ArrayList<>();

    @JsonCreator
    public HweImproved(@JsonProperty("storages") List<S> storages) {
        this.storages = storages;
    }

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
