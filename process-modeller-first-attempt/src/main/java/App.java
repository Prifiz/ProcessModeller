import entities.engine.LimitedStepsEngine;
import entities.engine.SimpleModellingEngine;
import entities.processes.AbstractProcess;
import entities.processes.SimpleLinearDiskConsumer;
import entities.system.Computer;
import entities.system.ComputerAdmin;
import entities.system.ComputerStateLogger;
import entities.system.SimpleSystem;
import entities.system.hwe.HweUsagePolicy;
import entities.system.hwe.storages.SimpleHdd;
import entities.system.hwe.storages.StorageDevice;

public class App {

    public static void main(String[] args) {

        System.out.println("Initializing system...");

        // fixme how to turn on/off computer?
/*
        System.out.println("BEFORE:");
        system.getComputers().forEach(comp -> System.out.println(comp.getLabel()));

        ModelSystemConfigurator configurator = new ModelSystemConfigurator(system);
        configurator.configure();

        System.out.println("AFTER:");
        system.getComputers().forEach(comp -> System.out.println(comp.getLabel()));
*/
        AbstractProcess simpleLinearDiskConsumer =
                new SimpleLinearDiskConsumer("simpleConsumer")
                        .consumesMbPerDay(500);

        StorageDevice storageDevice = new SimpleHdd("HDD_1", 1000)
                .canReadAt(200)
                .canWriteAt(100);
        Computer<StorageDevice> computer = new Computer<>("Comp with HDD"); // todo different devices types
        computer.addStorage(storageDevice);
        computer.addObserver(new ComputerStateLogger());
        computer.turnOn();

        ComputerAdmin admin = new ComputerAdmin(); // if needed??
        if (simpleLinearDiskConsumer.getHweUsagePolicy() == null) {
            HweUsagePolicy manualPolicy = new HweUsagePolicy(); // get configured on UI or file or wherever
            simpleLinearDiskConsumer.addPolicy(manualPolicy);
        }
        admin.registerProcess(computer, simpleLinearDiskConsumer);

        SimpleSystem system = new SimpleSystem();
        system.add(computer);

        long oneDayMillis = 24L * 3600L * 1000L;

        LimitedStepsEngine engine = new SimpleModellingEngine(system)
                .withCustomDeltaTime(oneDayMillis);
        engine.run(5);

    }
}
