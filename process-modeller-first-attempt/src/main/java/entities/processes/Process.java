package entities.processes;

import entities.system.hwe.HweEntry;

public interface Process<E extends HweEntry> {

    // run after processes; processes chain
    // run in parallel

    E useHweEntry(E hweEntry, long deltaTime);

    //boolean canBeRunInParallel();

}
