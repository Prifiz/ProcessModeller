import entities.engine.LimitedStepsEngine;
import entities.engine.SimpleModellingEngine;
import entities.processes.AbstractProcess;
import entities.processes.SimpleLinearDiskConsumer;
import entities.system.*;
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

        AbstractProcess consumer2 =
                new SimpleLinearDiskConsumer("consumer2")
                        .consumesMbPerDay(100);

        Computer<StorageDevice> computer = new Computer<>("Comp with HDD"); // todo different devices types

        StorageDevice storageDevice = new SimpleHdd("HDD_1", 1000)
                .canReadAt(200)
                .canWriteAt(100);

        computer.addStorage(storageDevice);


        computer.attachLogger(new ComputerStateLogger(computer));
        computer.attachLogger(new ProcessLogger(computer.getOs()));

        computer.turnOn();

        ComputerAdmin admin = new ComputerAdmin();
        admin.registerProcess(computer, simpleLinearDiskConsumer);
        admin.registerProcess(computer, consumer2);

        SimpleSystem system = new SimpleSystem();
        system.add(computer);

        long oneDayMillis = 24L * 3600L * 1000L;

        LimitedStepsEngine engine = new SimpleModellingEngine(system)
                .withCustomDeltaTime(oneDayMillis);
        engine.run(15);

    }
}
