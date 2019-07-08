package entities;

import exception.OutOfHweException;

import java.util.Random;

public class DefaultStandaloneTask extends Task {

    public DefaultStandaloneTask(int id) {
        super(id, TaskStatus.STOPPED);
    }

    private long nextRandom_512_1024() {
        Random random = new Random();
        return Math.round(1024*1024 + (1024*1024*1024 * random.nextFloat()));
    }

    @Override
    public void tick() throws OutOfHweException {
        long ramCapacity = assignee.getMemoryCapacity();
        long currentRam = assignee.getCurrentOccupiedMemory();


        assignee.consumeRam(id, nextRandom_512_1024());
    }
}
