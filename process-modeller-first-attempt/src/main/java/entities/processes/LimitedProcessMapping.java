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
            //long diskUsageSpeed = process.getDiskUsageSpeed();

        });
    }


    /*public void execute(long deltaTime) {
        List<AbstractDiskProcess> idleProcesses = new ArrayList<>();
        List<AbstractDiskProcess> workingProcesses = new ArrayList<>();
        long maxAllowedUsageSpeed = hdd.getWriteSpeed();
        //if (DiskUsageType.WRITE.equals(process.getDiskUsageType())) {
        //maxAllowedUsageSpeed = hdd.getWriteSpeed();
        //}
        long maxAllowedBytesToUse = maxAllowedUsageSpeed * deltaTime;

        for (AbstractDiskProcess process : processQueue) {
            long requestedBytesToUse = process.getBytesToUse(deltaTime);

            if (requestedBytesToUse < maxAllowedBytesToUse) {
                useHddSpace(requestedBytesToUse);
                maxAllowedBytesToUse -= requestedBytesToUse;
                workingProcesses.add(process);
            } else {
                if (maxAllowedBytesToUse == 0) {
                    idleProcesses.add(process);
                } else {
                    useHddSpace(maxAllowedBytesToUse);
                    process.setActualDiskUsageSpeed(maxAllowedUsageSpeed); // extra data to handle
                    maxAllowedBytesToUse = 0L;
                }
            }
        }
        processQueue.removeAll(idleProcesses);
    }*/

}
