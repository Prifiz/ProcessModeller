package entities;

import lombok.Getter;

@Getter
public class ProcessMapping {

    private Process process;
    private HweEntry hweEntry;

    public ProcessMapping(Process process, HweEntry hweEntry) {
        this.process = process;
        this.hweEntry = hweEntry;
    }
}
