package entities.processes;

import entities.system.hwe.HweEntry;
import entities.system.hwe.HweUsagePolicy;
import lombok.Getter;

@Getter
public abstract class AbstractProcess<E extends HweEntry> implements Process<E> {

    protected String processId;

    protected HweUsagePolicy hweUsagePolicy; // can be several policies; applied one upon one;
    // TODO change this field & method below in future!

    public void addPolicy(HweUsagePolicy manualPolicy) {
        this.hweUsagePolicy = manualPolicy;
    }

    // requirements

}
