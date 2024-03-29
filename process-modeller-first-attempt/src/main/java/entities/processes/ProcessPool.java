package entities.processes;

import entities.system.hwe.storages.StorageDevice;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ProcessPool<S extends StorageDevice> {

    private List<AbstractProcess<S>> processes;

    public ProcessPool() {
        this.processes = new LinkedList<>();
    }


}
