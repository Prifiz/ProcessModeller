package entities.system;

import entities.processes.SimpleLinearDiskConsumer;

public class SimpleLinearDiskConsumerConsoleLogger implements Logger {

    private SimpleLinearDiskConsumer simpleLinearDiskConsumer;

    public SimpleLinearDiskConsumerConsoleLogger(SimpleLinearDiskConsumer simpleLinearDiskConsumer) {
        this.simpleLinearDiskConsumer = simpleLinearDiskConsumer;
    }

    @Override
    public void doLog() {
        System.out.println(String.format("Process: [%s]\tConsumed space: [%s]",
                simpleLinearDiskConsumer.getProcessName(),
                simpleLinearDiskConsumer.getJustConsumed()));
    }
}
