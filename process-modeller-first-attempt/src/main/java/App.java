import entities.engine.LimitedStepsEngine;
import entities.engine.impl.SimpleModellingEngine;
import entities.processes.AbstractProcess;
import entities.processes.SimpleLinearDiskConsumer;
import entities.system.*;
import entities.system.hwe.storages.SimpleHdd;
import entities.system.hwe.storages.StorageDevice;
import entities.system.logger.impl.ComputerStateLogger;
import entities.system.logger.impl.SimpleLinearDiskConsumerConsoleLogger;

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
        AbstractProcess consumer500mbPerDay =
                new SimpleLinearDiskConsumer("500 per day")
                        .consumesMbPerDay(500);

        consumer500mbPerDay.attachLogger(new SimpleLinearDiskConsumerConsoleLogger(
                (SimpleLinearDiskConsumer) consumer500mbPerDay));

        AbstractProcess consumer100mbPerDay =
                new SimpleLinearDiskConsumer("100 per day")
                        .consumesMbPerDay(100);

        consumer100mbPerDay.attachLogger(new SimpleLinearDiskConsumerConsoleLogger((SimpleLinearDiskConsumer) consumer100mbPerDay));

        Computer<StorageDevice> computer = new Computer<>("Comp with HDD"); // todo different devices types

        StorageDevice storageDevice = new SimpleHdd("HDD_1", 1000)
                .canReadAt(200)
                .canWriteAt(100);

        computer.addStorage(storageDevice);

        StorageDevice storageDevice2 = new SimpleHdd("HDD_2", 2000)
                .canReadAt(200)
                .canWriteAt(100);

        computer.addStorage(storageDevice2);

        // todo for each process its own logger; for each hwe/computer maybe too
        computer.attachLogger(new ComputerStateLogger(computer));
        //computer.attachLogger(new ProcessLogger(computer.getOs()));

        computer.turnOn();

        ComputerAdmin admin = new ComputerAdmin();
        admin.registerProcess(computer, consumer500mbPerDay);
        admin.registerProcess(computer, consumer100mbPerDay);

        SimpleSystem system = new SimpleSystem();
        system.add(computer);

        long oneDayMillis = 24L * 3600L * 1000L;

        LimitedStepsEngine engine = new SimpleModellingEngine(system)
                .withCustomDeltaTime(oneDayMillis);
        engine.run(15);

//        EntityExporter jsonExporter = new ComputerJsonExporter();
//        String computerJson = jsonExporter.exportEntityToString(computer);

//        System.out.println("Serialized:");
//        System.out.println(computerJson);
//        jsonExporter.exportEntityToFile(computer, "computer.json");

//        EntityImporter jsonImporter = new ComputerJsonImporter();
//        Computer imported = (Computer) jsonImporter.importEntityFromFile("computer.json");
//        System.out.println("Deserialized:");
//        System.out.println(imported.toString());
    }

}
