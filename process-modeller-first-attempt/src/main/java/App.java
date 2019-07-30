import entities.Computer;
import entities.DefaultStandaloneTask;
import entities.Engine;
import entities.ModelSystemConfigurator;
import entities.Node;
import entities.OperatingSystemAbstraction;
import entities.SampleModellingEngine;
import entities.SimpleHdd;
import entities.SimpleLinearDiskConsumer;
import entities.StorageDevice;
import entities.Task;
import entities.modeller.ModelSystem;
import exception.OutOfHweException;

public class App {

    public static void main(String[] args) {

        System.out.println("Initializing system...");

       /* Node testNode = new Node("First")
                .withMinDefaultHwe()
                .enabled();

        Task defaultStandaloneTask = new DefaultStandaloneTask(1);
        defaultStandaloneTask.assign(testNode);

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

        SimpleLinearDiskConsumer simpleLinearDiskConsumer =
                new SimpleLinearDiskConsumer("simpleConsumer")
                        .consumesMbPerDay(500);

        StorageDevice storageDevice = new SimpleHdd("HDD_1", 1000)
                .canReadAt(200)
                .canWriteAt(100);
        Computer computer = new Computer("Comp with HDD");
        computer.addStorage(storageDevice);
        computer.turnOn();

        OperatingSystemAbstraction os = new OperatingSystemAbstraction().installToComputer(computer);
        os.registerProcess(simpleLinearDiskConsumer);

        os.runAllRegisteredProcesses();

        // fixme how to turn on/off computer?


        ModelSystem system = new ModelSystem();

        System.out.println("BEFORE:");
        system.getComputers().forEach(comp -> System.out.println(comp.getLabel()));

        ModelSystemConfigurator configurator = new ModelSystemConfigurator(system);
        configurator.configure();

        System.out.println("AFTER:");
        system.getComputers().forEach(comp -> System.out.println(comp.getLabel()));

        Engine engine = new SampleModellingEngine(system);
        engine.run();
    }
}
