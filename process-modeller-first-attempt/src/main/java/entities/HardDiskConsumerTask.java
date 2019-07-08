package entities;

import exception.OutOfHweException;

public class HardDiskConsumerTask extends Task {

    public HardDiskConsumerTask(int id, TaskStatus defaultStatus) {
        super(id, defaultStatus);
    }

    @Override
    public void tick() throws OutOfHweException {

    }
}
