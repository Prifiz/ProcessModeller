package entities.processes;

import entities.system.hwe.HweEntry;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ProcessExecutor<E extends HweEntry> {

    private AbstractProcess<E> process;
    private Optional<E> hweEntry;

    public ProcessExecutor(AbstractProcess<E> process, Optional<E> hweEntry) {
        this.process = process;
        this.hweEntry = hweEntry;
    }

    public void execute(long deltaTime) {
        hweEntry.ifPresent(entry -> process.useHweEntry(entry, deltaTime));
    }
}
