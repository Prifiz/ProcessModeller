package entities.processes;

import entities.system.Computer;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class OperatingSystemAbstraction {

    private Computer computer;
    private List<AbstractProcess> processes;

    public OperatingSystemAbstraction() {
        this.processes = new ArrayList<>();
    }

    public OperatingSystemAbstraction installToComputer(Computer computer) {
        this.computer = computer;
        return this;
    }


    public boolean registerProcess(AbstractProcess process) {
        Optional<AbstractProcess> proc = processes.stream()
                .filter(pr -> process.getProcessId().equals(pr.getProcessId()))
                .findFirst();
        if (proc.isPresent()) {
            return false;
        } else {
            this.processes.add(process);
            return true;
        }
    }

    public void registerAndRun(AbstractProcess process) throws IOException {
        if (registerProcess(process)) {
            runProcess(process);
        }
    }

    public void runProcess(AbstractProcess process) throws IOException {
        if (!computer.isOn()) {
            throw new IOException(
                    String.format("Can't run process. Node '%s' is OFF", computer.getLabel()));
        }

//        getProcessExecutor(process).run();
    }

//    private SimpleLinearDiskConsumerExecutor getProcessExecutor(AbstractProcess process) {
//        // some factory method
//        return new SimpleLinearDiskConsumerExecutor();
//    }

    public void runProcess(String processId) throws IOException {
        AbstractProcess process = findProcessById(processId);
        if (process != null) {
            runProcess(process);
        } else {
            // no such process
        }
    }

    private AbstractProcess findProcessById(String id) {
        return processes.stream()
                .filter(process -> id.equals(process.getProcessId()))
                .findFirst()
                .orElse(null);
    }

    public void runAllRegisteredProcesses() {
        for (AbstractProcess process : processes) {
            try {
                runProcess(process);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}
