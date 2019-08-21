package entities.processes;

import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class ProcessMapping {

    private SimpleHdd hdd;
    private Queue<AbstractProcess> processQueue;

    public ProcessMapping(SimpleHdd hdd) {
        this.hdd = hdd;
        this.processQueue = new LinkedList<>();
    }

    public void addProcess(AbstractProcess process) {
        this.processQueue.add(process);
    }
}
