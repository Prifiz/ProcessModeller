package entities;

import exception.OutOfHweException;

import java.util.HashMap;
import java.util.Map;

public class Hwe {

    private float cpu;
    private long ramCapacity;
    private Map<Integer, Long> occupiedRam = new HashMap<>();
    private float diskSpaceGb;

    public Hwe(float cpu, long ramCapacity, float diskSpaceGb) {
        this.cpu = cpu;
        this.ramCapacity = ramCapacity;
        this.diskSpaceGb = diskSpaceGb;
    }

    public void freeRamByTask(long bytesToFree) {

    }

    public long getTotalOccupiedRam() {
        return occupiedRam.values().stream().reduce(0L, Long::sum);
    }

    public void utilizeRamByTask(int taskId, long bytesToConsume) throws OutOfHweException {
        long totalOccupiedRam = getTotalOccupiedRam();
        long freeMemory = ramCapacity - totalOccupiedRam;
        if(freeMemory < bytesToConsume) {
            throw new OutOfHweException("No free RAM to utilize");
        }
        long taskRam = occupiedRam.getOrDefault(taskId, 0L);
        this.occupiedRam.put(taskId, taskRam + bytesToConsume);
    }


    public long getRamCapacity() {
        return ramCapacity;
    }


}
