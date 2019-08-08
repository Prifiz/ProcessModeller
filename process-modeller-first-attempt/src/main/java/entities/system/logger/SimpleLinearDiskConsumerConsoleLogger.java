package entities.system.logger;

import entities.processes.SimpleLinearDiskConsumer;
import lombok.Getter;

@Getter
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
