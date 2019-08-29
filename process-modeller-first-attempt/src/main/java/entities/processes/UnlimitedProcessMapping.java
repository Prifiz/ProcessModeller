package entities.processes;

import entities.processes.storages.AbstractDiskProcess;
import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.*;

@Getter
public class UnlimitedProcessMapping {

    private SimpleHdd hdd;
    private List<AbstractDiskProcess> processQueue;
    private List<AbstractDiskProcess> healthyProcesses = new ArrayList<>();
    private AbstractDiskProcess firstOverloadingProcess;

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
        hdd.consumeSpace(bytesToConsume);
    }

    public Collection<AbstractDiskProcess> getNotExecutedProcesses() {
        List<AbstractDiskProcess> result = new ArrayList<>();
        Collections.copy(result, processQueue);
        result.removeAll(healthyProcesses);
        return result;
    }

    public void execute(long deltaTime) {
        healthyProcesses.clear();
        firstOverloadingProcess = null;
        long maxAllowedUsageSpeed = hdd.getWriteSpeed();
        long maxAllowedBytesToUse = maxAllowedUsageSpeed * deltaTime;
        System.out.println("Working with HDD: " + hdd.getLabel());
        for (AbstractDiskProcess process : processQueue) {
            System.out.println("Trying to run process: " + process.getProcessName());
            long requestedBytesToUse = process.getBytesToUse(deltaTime);
            if (hdd.getFreeSpace() < requestedBytesToUse) {
                System.out.println("No free space");
                firstOverloadingProcess = process;
                return;
            }
            if (requestedBytesToUse <= maxAllowedBytesToUse) {
                useHddSpace(requestedBytesToUse);
                maxAllowedBytesToUse -= requestedBytesToUse;
                healthyProcesses.add(process);
                System.out.println("Process done");
            } else {
                System.out.println(String.format("Disk overload by process: [%s]", process.getProcessName()));
                firstOverloadingProcess = process;
                return;
            }
        }
    }
}