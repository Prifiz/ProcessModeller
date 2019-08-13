package entities.system.logger.impl;

import entities.processes.SimpleLinearDiskConsumer;
import entities.system.logger.Logger;
import lombok.Getter;

@Getter
public class SimpleLinearDiskConsumerConsoleLogger implements Logger {

    private SimpleLinearDiskConsumer simpleLinearDiskConsumer;

    public SimpleLinearDiskConsumerConsoleLogger(SimpleLinearDiskConsumer simpleLinearDiskConsumer) {
        this.simpleLinearDiskConsumer = simpleLinearDiskConsumer;
    }

    @Override
    public void doLog() {
        System.out.println(String.format("Process: [%s]\tUses HDD: [???]\tConsumed space: [%s]",
                simpleLinearDiskConsumer.getProcessName(),
                simpleLinearDiskConsumer.getJustConsumed()));
    }
}
