package entities.processes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entities.system.logger.LoggedEntity;
import entities.system.logger.Logger;
import entities.system.hwe.HweEntry;
import entities.system.hwe.HweUsagePolicy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractProcess<E extends HweEntry> implements Process<E>, LoggedEntity {

    // retention LoggedEntity?

    protected String processId;
    protected String processName;
    @JsonIgnore
    protected List<Logger> loggers;

    public AbstractProcess(String processName) {
        this.processName = processName;
        this.loggers = new ArrayList<>();
    }


    protected HweUsagePolicy hweUsagePolicy; // can be several policies; applied one upon one;

    // TODO change this field & method below in future!

    public void addPolicy(HweUsagePolicy manualPolicy) {
        this.hweUsagePolicy = manualPolicy;
    }

    // requirements

    public void attachLogger(Logger logger) {
        this.loggers.add(logger);
    }

    public void notifyAllLoggers() {
        loggers.forEach(Logger::doLog);
    }

}
