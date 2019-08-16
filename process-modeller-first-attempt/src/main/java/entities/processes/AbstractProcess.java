package entities.processes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import entities.processes.storages.SimpleLinearDiskConsumer;
import entities.system.hwe.HweEntry;
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
public abstract class AbstractProcess<E extends HweEntry> implements Process<E>, LoggedEntity, Cloneable {
// todo use custom deserializer or some other staff for abstract class
    // retention LoggedEntity?

    @JsonProperty
    protected String processId;
    @JsonProperty
    protected String processName;
    @JsonIgnore
    protected List<Logger> loggers = new ArrayList<>();


    public AbstractProcess(String processName) {
        this.processName = processName;
        //this.loggers = new ArrayList<>();
    }

    @JsonCreator
    public AbstractProcess(
            @JsonProperty("processName") String processName,
            @JsonProperty("processId") String processId) {
        this.processName = processName;
        this.processId = processId;
    }

    protected long mbytesToBytes(long mbytes) {
        return mbytes * 1024 * 1024;
    } // fixme replace with javax.metrics

    protected long bytesToMb(long bytes) {
        return bytes / 1024 / 1024;
    }

    // TODO change this field & method below in future!

    // requirements

    public void attachLogger(Logger logger) {
        this.loggers.add(logger);
    }

    public void notifyAllLoggers() {
        loggers.forEach(Logger::doLog);
    }

    // TODO maybe deep clone() implementation needed
    // FIXME fix unchecked issue
    @Override
    protected AbstractProcess<E> clone() throws CloneNotSupportedException {
        return (AbstractProcess<E>) super.clone();
    }
}
