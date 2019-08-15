package entities.system.logger.impl;

import entities.processes.ProcessExecutor;
import entities.processes.storages.SimpleLinearDiskConsumer;
import entities.system.hwe.storages.StorageDevice;
import entities.system.logger.Logger;

public class ProcessExecutorConsoleLogger implements Logger {

    private ProcessExecutor processExecutor;

    public ProcessExecutorConsoleLogger(ProcessExecutor processExecutor) {
        this.processExecutor = processExecutor;
    }

    // fixme extremely rigid, will produce cast exception!
    // @Logged retention?
    @Override
    public void doLog() {
        System.out.println(
                String.format(
                        "Executed process: [%s]\tUses HWE: [%s]\tConumed space: [%s]",
                        processExecutor.getProcess().getProcessName(),
                        ((StorageDevice)processExecutor.getHweEntry().get()).getLabel(),
                        ((SimpleLinearDiskConsumer)processExecutor.getProcess()).getJustConsumed()));
    }
}
