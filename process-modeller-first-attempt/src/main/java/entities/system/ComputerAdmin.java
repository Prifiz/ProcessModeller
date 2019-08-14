package entities.system;

import entities.processes.AbstractProcess;
import entities.system.hwe.policies.HweUsagePolicy;

public class ComputerAdmin {

    // can encapsulate complex scenarios of computer usage
    // maybe rename to ComputerManager

    public void registerProcess(Computer<?> computer, AbstractProcess process, HweUsagePolicy policy) {
        computer.registerProcess(process, policy);
    }


}
