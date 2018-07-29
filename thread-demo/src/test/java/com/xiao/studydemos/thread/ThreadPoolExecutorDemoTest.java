package com.xiao.studydemos.thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang
 * 2018.02.11 17:03
 *
 * @Version 1.0
 */
public class ThreadPoolExecutorDemoTest {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void threadPool() throws Exception {
        assertEquals(0,CAPACITY);
        BlockingQueue queue = new LinkedBlockingDeque(10);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10,20, 3L,TimeUnit.MINUTES,queue);
        Executors.newCachedThreadPool();
    }

    @Test
    public void testReetrentLock() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock(true);


        reentrantLock.lock();


        reentrantLock.unlock();
    }
}