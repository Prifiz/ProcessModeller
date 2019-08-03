import entities.processes.AbstractProcess;
import entities.system.Computer;
import entities.system.ComputerAdmin;
import entities.engine.Engine;
import entities.system.hwe.HweUsagePolicy;
import entities.engine.SimpleModellingEngine;
import entities.system.hwe.storages.SimpleHdd;
import entities.processes.SimpleLinearDiskConsumer;
import entities.system.hwe.storages.StorageDevice;
import entities.system.SimpleSystem;

public class App {

    public static void main(String[] args) {

        System.out.println("Initializing system...");

       /* Node testNode = new Node("First")
                .withMinDefaultHwe()
                .enabled();



        int timeSeconds = 0;

        final int TICK = 1;
        final int EVAL_TIMEOUT = 30000000;

        System.out.println("Memory Capacity: " + testNode.getMemoryCapacity());
        System.out.println("Initial Free Memory: "
                + (testNode.getMemoryCapacity() - testNode.getCurrentOccupiedMemory()));

        while (timeSeconds < EVAL_TIMEOUT) {
            try {
                defaultStandaloneTask.tick();
            } catch (OutOfHweException ex) {
                System.out.println(String.format("Node with label '%s' reported an error:", testNode.getLabel()));
                System.out.println(ex.getMessage());
                break;
            }
            System.out.println((testNode.getMemoryCapacity() - testNode.getCurrentOccupiedMemory()));
            timeSeconds += TICK;
        }

        System.out.println("Ended With Free Memory: "
                + (testNode.getMemoryCapacity() - testNode.getCurrentOccupiedMemory()));
        System.out.println("Took modelling time (days.): " + timeSeconds / 3600 / 24);
        System.out.println("Took modelling time (hrs.): " + timeSeconds / 3600);
        System.out.println("Took modelling time (mins.): " + timeSeconds / 60);*/


//        Node second = new Node("Second")
//                .withMinDefaultHwe()
//                .enabled();


//        testNode.enable();
//        second.enable();


//        OperatingSystemAbstraction os = new OperatingSystemAbstraction().installToComputer(computer);
//        os.registerProcess(simpleLinearDiskConsumer);
//
//        os.runAllRegisteredProcesses();

        // fixme how to turn on/off computer?


//        ModelSystem system = new ModelSystem();
//
//        System.out.println("BEFORE:");
//        system.getComputers().forEach(comp -> System.out.println(comp.getLabel()));
//
//        ModelSystemConfigurator configurator = new ModelSystemConfigurator(system);
//        configurator.configure();
//
//        System.out.println("AFTER:");
//        system.getComputers().forEach(comp -> System.out.println(comp.getLabel()));



        AbstractProcess simpleLinearDiskConsumer =
                new SimpleLinearDiskConsumer("simpleConsumer")
                        .consumesMbPerDay(500);

        StorageDevice storageDevice = new SimpleHdd("HDD_1", 1000)
                .canReadAt(200)
                .canWriteAt(100);
        Computer<StorageDevice> computer = new Computer<>("Comp with HDD"); // todo different devices types
        computer.addStorage(storageDevice);
        computer.turnOn();

        ComputerAdmin admin = new ComputerAdmin(); // if needed??
        if (simpleLinearDiskConsumer.getHweUsagePolicy() == null) {
            HweUsagePolicy manualPolicy = new HweUsagePolicy(); // get configured on UI or file or wherever
            simpleLinearDiskConsumer.addPolicy(manualPolicy);
        }
        admin.registerProcess(computer, simpleLinearDiskConsumer);

        SimpleSystem system = new SimpleSystem();
        system.add(computer);

        Engine engine = new SimpleModellingEngine(system);
        engine.run();

    }
}
