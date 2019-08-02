package entities;

import lombok.Getter;

@Getter
public abstract class ProcessExecutor {

    private AbstractProcess process;
    private HweEntry hweEntry;

    public ProcessExecutor(AbstractProcess process, HweEntry hweEntry) {
        this.process = process;
        this.hweEntry = hweEntry;
    }

    public abstract void execute(long deltaTime);
}
