package entities;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
public class ProcessPool {

    private List<AbstractProcess> processes;

    public ProcessPool() {
        this.processes = new LinkedList<>();
    }


}
