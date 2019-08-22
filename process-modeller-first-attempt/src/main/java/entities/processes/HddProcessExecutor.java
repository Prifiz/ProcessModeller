package entities.processes;

import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.List;

@Getter
public class HddProcessExecutor {

    private ProcessTable processTable;

    public HddProcessExecutor(List<SimpleHdd> drives) {
        processTable = new ProcessTable(drives);
    }

    public void register(AbstractProcess process, SimpleHdd hdd) {
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

    public void unregisterFromHdd(AbstractProcess process, SimpleHdd hdd) {
        processTable.unassignProcessFromHdd(process, hdd);
    }

    public void unregisterFromAllHdds(AbstractProcess process) {
        processTable.unassignProcessFromAllHdds(process);
    }

    //public void executeAllForHdd()

}
