package entities;

public class ComputerAdmin {

    // can encapsulate complex scenarios of computer usage
    // maybe rename to ComputerManager

    public void registerProcess(Computer computer, AbstractProcess process) {
        computer.addProcess(process);
    }


}
