package entities;

public class ComputerAdmin {

    public void registerProcess(Computer computer, AbstractProcess process) {
        computer.addProcess(process);
    }


}
