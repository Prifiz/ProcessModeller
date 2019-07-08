package entities;

import exception.OutOfHweException;

public abstract class Task {

    protected int id;

    protected TaskStatus status;
    protected TaskStatus defaultStatus;

    protected Node assignee;

    public Task(int id, TaskStatus defaultStatus) {
        this.id = id;
        this.defaultStatus = defaultStatus;
        this.status = defaultStatus;
    }

    public void assign(Node assignee) {
        this.assignee = assignee;
    }

    public abstract void tick() throws OutOfHweException;

}
