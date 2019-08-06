package entities.system;

import entities.system.hwe.storages.StorageDevice;

public class ComputerStateLogger implements Logger {

    private Computer computer;

    public ComputerStateLogger(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void doLog() {

        computer.getHwe().getStorages().forEach(storage -> {
//                System.out.println(String.format("Storage '%s': ", storage.getLabel()));
//                System.out.println(String.format("Capacity: [%s] bytes", storage.getCapacity()));
//                System.out.println(String.format("Used space: [%s] bytes", storage.getUsedSpace()));
            System.out.println(
                    String.format(
                            "Total storage used space: [%s]",
                            ((StorageDevice) storage).getUsedSpace() / 1024 / 1024));
        });

    }
}
