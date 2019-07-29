package entities;

import java.util.ArrayList;
import java.util.List;

public class HweImproved {

    protected List<StorageDevice> storages;

    public HweImproved() {
        this.storages = new ArrayList<>();
    }

    public void addStorage(StorageDevice storageDevice) {
        this.storages.add(storageDevice);
    }

}
