package entities.system;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class SimpleSystem {
    private List<Computer> computers;

    public SimpleSystem() {
        this.computers = new LinkedList<>();
    }

    public void add(Computer computer) {
        this.computers.add(computer);
    }
}
