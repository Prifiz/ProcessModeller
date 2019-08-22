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

    public void removeProcess(AbstractProcess process) {
        this.processQueue.removeIf(processInQueue ->
                processInQueue.getProcessName().equals(process.getProcessName()));
    }

    public void execute(long deltaTime) {
        // todo check how much can be done during this delta, how many processes it can cover
        // remove all completed processes from queue
        // save process execution state after deltaTime (from which point it should proceed)
    }

}
