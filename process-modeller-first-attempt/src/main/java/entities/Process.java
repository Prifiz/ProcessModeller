package entities;

public interface Process<E extends HweEntry> {

    // run after processes; processes chain
    // run in parallel

    E useHweEntry(E hweEntry, long deltaTime);

    boolean canBeRunInParallel();

}
