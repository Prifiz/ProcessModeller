package entities;

import exception.OutOfHweException;

public class Node {

    private static int count = 0;

    private final int id;
    private NodeStatus status = NodeStatus.OFF;

    private String label;

    private Hwe hwe;

    public Node(String label) {
        // not a thread-safe!
        count += 1;
        this.id = count;
        this.label = label;
    }

    public Node withMinDefaultHwe() {
        this.hwe = new MinDefaultHwe();
        return this;
    }

    public Node enabled() {
        enable();
        return this;
    }

    public void enable() {
        this.status = NodeStatus.ON;
    }

    public void disable() {
        this.status = NodeStatus.OFF;
    }

    public void consumeRam(int taskId, long bytesToConsume) throws OutOfHweException {
        this.hwe.utilizeRamByTask(taskId, bytesToConsume);
    }

    public long getCurrentOccupiedMemory() {
        return this.hwe.getTotalOccupiedRam();
    }

    public long getMemoryCapacity() {
        return this.hwe.getRamCapacity();
    }

    public String getLabel() {
        return label;
    }


}
