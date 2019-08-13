package entities.system.logger;

import entities.system.Computer;
import entities.system.hwe.storages.StorageDevice;
import lombok.Getter;

@Getter
public class ComputerStateLogger implements Logger {

    private Computer computer;

    public ComputerStateLogger(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void doLog() {

        System.out.println(
                String.format(
                        "OS current time for computer [%s] is [%s]",
                        computer.getLabel(),
                        computer.getOs().getCurrentOsTime()));

        computer.getHwe().getStorages().forEach(storage -> {
//                System.out.println(String.format("Storage '%s': ", storage.getLabel()));
//                System.out.println(String.format("Capacity: [%s] bytes", storage.getCapacity()));
//                System.out.println(String.format("Used space: [%s] bytes", storage.getUsedSpace()));

            System.out.println(
                    String.format(
                            "Total storage [%s] used space: [%s]",
                            ((StorageDevice) storage).getLabel(),
                            ((StorageDevice) storage).getUsedSpace() / 1024 / 1024));
        });

    }
}
