package entities.processes;

import entities.system.hwe.storages.SimpleHdd;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Getter
public class ProcessTable {

    private List<ProcessMapping> mappings;

    public ProcessTable(List<SimpleHdd> drives) {
        mappings = new ArrayList<>();
        drives.forEach(hdd -> {
            mappings.add(new ProcessMapping(hdd));
        });
    }

    public void assignProcessToHdd(AbstractProcess process, SimpleHdd hdd) {
        mappings.stream()
                .filter(mapping -> hdd.equals(mapping.getHdd()))
                .findFirst()
                .ifPresent(foundHdd -> foundHdd.addProcess(process));
    }

    public void unassignProcessFromHdd(AbstractProcess process, SimpleHdd hdd) {
        mappings.stream()
                .filter(mapping -> hdd.getLabel().equals(mapping.getHdd().getLabel()))
                .findFirst()
                .ifPresent(mapping -> mapping.removeProcess(process));
    }

    public void unassignProcessFromAllHdds(AbstractProcess process) {
        mappings.forEach(mapping -> {
            mapping.getProcessQueue().forEach(processInQueue -> {
                if(processInQueue.getProcessName().equals(process.getProcessName())) {
                    mapping.removeProcess(process);
                }
            });
        });

//        mappings.forEach(mapping -> {
//            System.out.println(mapping.getHdd().getLabel());
//            printProcessQueue(mapping.getProcessQueue());
//        });

    }

    public void printContents() {
        mappings.forEach(mapping -> {
            System.out.println(String.format("HDD: [%s]", mapping.getHdd().getLabel()));
            printProcessQueue(mapping.getProcessQueue());
        });
    }

    private void printProcessQueue(Queue<AbstractProcess> processQueue) {
        processQueue.forEach(process -> System.out.println("\t" + process.getProcessName()));
    }
}
