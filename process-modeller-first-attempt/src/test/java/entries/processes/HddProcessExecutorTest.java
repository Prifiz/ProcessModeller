package entries.processes;

import com.google.common.collect.ImmutableList;
import entities.processes.AbstractProcess;
import entities.processes.HddProcessExecutor;
import entities.processes.storages.SimpleLinearDiskConsumer;
import entities.system.hwe.storages.SimpleHdd;
import org.junit.Assert;
import org.junit.Test;

public class HddProcessExecutorTest {

    @Test
    public void smokeTest() {

        AbstractProcess consumer1 = new SimpleLinearDiskConsumer("Consumer_1");
        AbstractProcess consumer2 = new SimpleLinearDiskConsumer("Consumer_2");
        SimpleHdd hdd1 = new SimpleHdd("HDD1", 1000);
        SimpleHdd hdd2 = new SimpleHdd("HDD2", 2000);

        HddProcessExecutor hddProcessExecutor = new HddProcessExecutor(ImmutableList.of(hdd1, hdd2));

        hddProcessExecutor.register(consumer1, hdd1);
        hddProcessExecutor.register(consumer1, hdd2);
        hddProcessExecutor.register(consumer2, hdd2);

        hddProcessExecutor.getProcessTable().printContents();

        //hddProcessExecutor.unregisterFromAllHdds(consumer1);
        hddProcessExecutor.unregisterFromHdd(consumer1, hdd2);

        System.out.println("AFTER:");
        hddProcessExecutor.getProcessTable().printContents();

        Assert.assertTrue(true);
    }
}
