package entities.system;

import entities.processes.AbstractProcess;
import entities.system.Computer;
import entities.system.hwe.HweUsagePolicy;

public class ComputerAdmin {

    // can encapsulate complex scenarios of computer usage
    // maybe rename to ComputerManager

    public void registerProcess(Computer computer, AbstractProcess process) {
        if (process.getHweUsagePolicy() == null) {
            HweUsagePolicy manualPolicy = new HweUsagePolicy(); // get configured on UI or file or wherever
            process.addPolicy(manualPolicy);
        }
        computer.addProcess(process);
    }


}
