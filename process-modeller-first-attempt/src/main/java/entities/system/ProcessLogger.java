package entities.system;

import entities.processes.OperatingSystem;
import entities.system.hwe.storages.StorageDevice;

public class ProcessLogger implements Logger {

    private OperatingSystem<StorageDevice> os;

    public ProcessLogger(OperatingSystem<StorageDevice> os) {
        this.os = os;
    }

    @Override
    public void doLog() {
        System.out.println(String.format("Current OS time: %s", os.getCurrentOsTime()));
        os.getProcesses().forEach(process -> {
            System.out.println(String.format("Process: [%s]", process.getProcessName()));
        });
    }
}
