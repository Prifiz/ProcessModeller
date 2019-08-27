package entities.processes;

public interface LimitedProcess extends Process {
    long getTimeLimit();
}
