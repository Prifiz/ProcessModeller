package entries.processes;

import com.google.common.collect.ImmutableList;
import entities.processes.AbstractProcess;
import entities.processes.HddProcessExecutor;
import entities.processes.storages.AbstractDiskProcess;
import entities.processes.storages.SimpleLinearDiskConsumer;
import entities.system.hwe.storages.SimpleHdd;
import org.junit.Assert;
import org.junit.Test;

public class HddProcessExecutorTest {

    @Test
    public void smokeTest() {

        AbstractDiskProcess consumer1 = new SimpleLinearDiskConsumer("Consumer_1");
        AbstractDiskProcess consumer2 = new SimpleLinearDiskConsumer("Consumer_2");
        SimpleHdd hdd1 = new SimpleHdd("HDD1", 1000);
        SimpleHdd hdd2 = new SimpleHdd("HDD2", 2000);

        HddProcessExecutor hddProcessExecutor = new HddProcessExecutor(ImmutableList.of(hdd1, hdd2));

        hddProcessExecutor.register(consumer1, hdd1);
        hddProcessExecutor.register(consumer1, hdd2);
        hddProcessExecutor.register(consumer2, hdd2);

        System.out.println("==========");
        System.out.println("BEFORE:");
        System.out.println("==========");
        hddProcessExecutor.getProcessTable().printContents();

//        hddProcessExecutor.unregisterFromAllHdds(consumer2);
//        hddProcessExecutor.unregisterFromAllHdds(consumer1);
//        hddProcessExecutor.unregisterFromHdd(consumer1, hdd2);
//        hddProcessExecutor.register(consumer2, hdd1);

        hddProcessExecutor.executeProcessesOnHdd(hdd1, 1000L);

        System.out.println("==========");
        System.out.println("AFTER:");
        System.out.println("==========");
        hddProcessExecutor.getProcessTable().printContents();
        System.out.println("==========");

        Assert.assertTrue(true);
    }
}
