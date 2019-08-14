package entities.processes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import entities.system.hwe.HweEntry;
import entities.system.hwe.policies.HweUsagePolicy;
import entities.system.logger.LoggedEntity;
import entities.system.logger.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@JsonDeserialize(as = SimpleLinearDiskConsumer.class) // fixme add custom deserializer for other subclasses
// OR change model: construct concrete based on concrete type fields
public abstract class AbstractProcess<E extends HweEntry> implements Process<E>, LoggedEntity {
// todo use custom deserializer or some other staff for abstract class
    // retention LoggedEntity?

    @JsonProperty
    protected String processId;
    @JsonProperty
    protected String processName;
    @JsonProperty
    protected HweUsagePolicy hweUsagePolicy; // can be several policies; applied one upon one;
    @JsonIgnore
    protected List<Logger> loggers = new ArrayList<>();


    public AbstractProcess(String processName) {
        this.processName = processName;
        //this.loggers = new ArrayList<>();
    }

    @JsonCreator
    public AbstractProcess(
            @JsonProperty("processName") String processName,
            @JsonProperty("hweUsagePolicy") HweUsagePolicy hweUsagePolicy,
            @JsonProperty("processId") String processId) {
        this.processName = processName;
        this.hweUsagePolicy = hweUsagePolicy;
        this.processId = processId;
    }


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
