package entities;

import java.util.LinkedList;
import java.util.List;

public class OperatingSystem implements ProcessManager {

    private List<ProcessMapping> processMappings;

    public OperatingSystem() {
        processMappings = new LinkedList<>();
    }


    @Override
    public void registerProcess(AbstractProcess process) {
        HweUsagePolicy policy = process.getHweUsagePolicy();
        policy.getAllowedHdds().forEach(hdd -> processMappings.add(new ProcessMapping(process, hdd)));
    }


}
