package entities.processes;

import entities.processes.storages.AbstractDiskProcess;
import entities.processes.storages.DiskUsageType;
import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
public class UnlimitedProcessMapping {

    private SimpleHdd hdd;
    private Queue<AbstractDiskProcess> processQueue;

    public UnlimitedProcessMapping(SimpleHdd hdd) {
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

    private void useHddSpace(long bytesToConsume) {

    }


    public void execute(long deltaTime) {
        List<AbstractDiskProcess> idleProcesses = new ArrayList<>();
        List<AbstractDiskProcess> workingProcesses = new ArrayList<>();
        long maxAllowedUsageSpeed = hdd.getWriteSpeed();
        //if (DiskUsageType.WRITE.equals(process.getDiskUsageType())) {
            //maxAllowedUsageSpeed = hdd.getWriteSpeed();
        //}
        for(AbstractDiskProcess process : processQueue) {
            long processDiskUsageSpeed = process.getDiskUsageSpeed();

            if (processDiskUsageSpeed < maxAllowedUsageSpeed) {// not by speed but by bytesAllowedToChange
                useHddSpace(process.getBytesToUse(deltaTime));
                maxAllowedUsageSpeed -= processDiskUsageSpeed;
                workingProcesses.add(process);
            } else {
                long allowedBytesToUse = maxAllowedUsageSpeed * deltaTime;
                if (allowedBytesToUse == 0) {
                    idleProcesses.add(process);
                } else {
                    useHddSpace(allowedBytesToUse);
                    process.setActualDiskUsageSpeed(maxAllowedUsageSpeed); // extra data to handle
                    maxAllowedUsageSpeed = 0L;
                }
            }
        }
        processQueue.removeAll(idleProcesses);
    }
}
