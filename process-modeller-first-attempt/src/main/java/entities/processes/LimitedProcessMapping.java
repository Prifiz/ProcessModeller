package entities.processes;

import entities.processes.storages.AbstractDiskProcess;
import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class LimitedProcessMapping {// todo refactor together with UnlimitedProcessMapping

    private SimpleHdd hdd;
    private Queue<AbstractDiskProcess> processQueue;

    public LimitedProcessMapping(SimpleHdd hdd) {
        this.hdd = hdd;
        this.processQueue = new LinkedList<>();
    }

    public void addProcess(AbstractDiskProcess process) {
        this.processQueue.add(process);
    }

    public void removeProcess(AbstractDiskProcess process) {
        this.processQueue.removeIf(processInQueue ->
                processInQueue.getProcessName().equals(process.getProcessName()));
    }

    public void execute(long deltaTime) {
        // todo check how much can be done during this delta, how many processes it can cover
        // remove all completed processes from queue
        // save process execution state after deltaTime (from which point it should proceed)
        processQueue.forEach(process -> {
            long diskUsageSpeed = process.getDiskUsageSpeed();

        });
    }

}
