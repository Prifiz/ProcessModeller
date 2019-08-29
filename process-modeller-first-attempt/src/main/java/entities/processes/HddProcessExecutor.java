package entities.processes;

import entities.processes.storages.AbstractDiskProcess;
import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.List;

@Getter
public class HddProcessExecutor {

    private ProcessTable processTable;

    public HddProcessExecutor(List<SimpleHdd> drives) {
        processTable = new ProcessTable(drives);
    }

    public void register(AbstractDiskProcess process, SimpleHdd hdd) {
        try {
            processTable.assignProcessToHdd(process.clone(), hdd);
        } catch (CloneNotSupportedException | IllegalArgumentException ex) {
            System.out.println(String.format(
                    "Can't register process [%s] to hdd [%s]", process.getProcessName(), hdd.getLabel()));
            System.out.println(ex.getMessage());
        }
    }

    public void executeProcessesOnHdd(SimpleHdd hdd, long deltaTime) {
        processTable.runHddProcesses(hdd, deltaTime);
    }

    public void unregisterFromHdd(AbstractDiskProcess process, SimpleHdd hdd) {
        processTable.unassignProcessFromHdd(process, hdd);
    }

    public void unregisterFromAllHdds(AbstractDiskProcess process) {
        processTable.unassignProcessFromAllHdds(process);
    }

    //public void executeAllForHdd()

}
