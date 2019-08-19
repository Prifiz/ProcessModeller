package entities.processes;

import entities.system.hwe.HweEntry;

public abstract class AbstractLimitedProcess extends AbstractProcess {

    private final long timeLimit;

    public AbstractLimitedProcess(String processName, long timeLimit) {
        super(processName);
        this.timeLimit = timeLimit;
    }

    @Override
    public void useHweEntry(HweEntry hweEntry, long deltaTime) {

    }
}
