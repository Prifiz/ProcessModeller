package entities;

import lombok.Getter;

public abstract class AbstractProcess implements Process {

    @Getter
    protected String processId;

    public abstract HweUsagePolicy getHweUsagePolicy();

    public abstract void addPolicy(HweUsagePolicy manualPolicy);

    // requirements

}
