package entities.processes;

import entities.system.hwe.HweEntry;
import entities.system.logger.LoggedEntity;
import entities.system.logger.Logger;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class ProcessExecutor<E extends HweEntry> implements LoggedEntity {

    private AbstractProcess<E> process;
    private Optional<E> hweEntry;

    private List<Logger> loggers;

    public ProcessExecutor(AbstractProcess<E> process, Optional<E> hweEntry) {
        this.process = process;
        this.hweEntry = hweEntry;
        this.loggers = new ArrayList<>();
    }

    public void execute(long deltaTime) {
        hweEntry.ifPresent(entry -> process.useHweEntry(entry, deltaTime));
    }

    @Override
    public void attachLogger(Logger logger) {
        this.loggers.add(logger);
    }

    @Override
    public void notifyAllLoggers() {
        loggers.forEach(Logger::doLog);
    }
}
