package entities.system;

public class ComputerStateLogger implements Observer {

    @Override
    public void react(Observable observable) {
        if (Computer.class.equals(observable.getClass())) {
            ((Computer<?>) observable).getHwe().getStorages().forEach(storage -> {
//                System.out.println(String.format("Storage '%s': ", storage.getLabel()));
//                System.out.println(String.format("Capacity: [%s] bytes", storage.getCapacity()));
//                System.out.println(String.format("Used space: [%s] bytes", storage.getUsedSpace()));
                System.out.println(storage.getUsedSpace() / 1024 / 1024);
            });
        }
    }
}
