package entries.processes;

import com.google.common.collect.ImmutableList;
import entities.processes.HddProcessExecutor;
import entities.processes.storages.AbstractDiskProcess;
import entities.processes.storages.SimpleLinearDiskConsumer;
import entities.system.hwe.storages.SimpleHdd;
import org.junit.Assert;
import org.junit.Test;

public class HddProcessExecutorTest {

    @Test
    public void smokeTest() {

        AbstractDiskProcess consumer1 = new SimpleLinearDiskConsumer("Consumer_1").consumesMbPerDay(10240);
        AbstractDiskProcess consumer2 = new SimpleLinearDiskConsumer("Consumer_2").consumesMbPerDay(20480);
        AbstractDiskProcess consumer3 = new SimpleLinearDiskConsumer("Consumer_3").consumesMbPerDay(40960);
        AbstractDiskProcess consumer4 = new SimpleLinearDiskConsumer("Consumer_4").consumesMbPerDay(81920);
        SimpleHdd hdd1 = new SimpleHdd("HDD1", 1000).canWriteAt(100);
        SimpleHdd hdd2 = new SimpleHdd("HDD2", 2000);

        HddProcessExecutor hddProcessExecutor = new HddProcessExecutor(ImmutableList.of(hdd1, hdd2));

        hddProcessExecutor.register(consumer1, hdd1);
        hddProcessExecutor.register(consumer2, hdd1);
        hddProcessExecutor.register(consumer3, hdd1);
        hddProcessExecutor.register(consumer4, hdd1);
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

        for (int i = 0; i < 15; i++) {
            //hddProcessExecutor.executeProcessesOnHdd(hdd1, 1000L);
            hddProcessExecutor.executeProcessesOnHdd(hdd1, 1000L * 3600 * 24);
            System.out.println(String.format("HDD: [%s]\tUSED SPACE: [%s] Mb", hdd1.getLabel(), hdd1.getUsedSpace()/1024/1024));
        }

        System.out.println("==========");
        System.out.println("AFTER:");
        System.out.println("==========");
        hddProcessExecutor.getProcessTable().printContents();
        System.out.println("==========");

        Assert.assertTrue(true);
    }
}
