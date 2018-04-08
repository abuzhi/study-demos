package com.xiao.demo.commons.utils;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZkClientTest {
    private ZkClient zkClient = new ZkClient();

    @Before
    public void setUp() throws Exception {
        zkClient.init();
    }

    @Test
    public void testLock() {
        String path = "/test_lock";
        boolean lock = zkClient.lock(path);
        if (lock) {
            try {
                //配置信息
                System.out.println("get lock...");
            } catch (Exception e) {
                System.out.println(" error ...");
            } finally {
                zkClient.unlock(path);
            }
        }
    }

    @Test
    public void testLockVersion() {
        String path = "/test_lock";
        DateTime time = new DateTime();
        String version = time.toString("yyyyMMddHH");

        boolean lock = zkClient.lockWithTimeVersion(path,version);
        if (lock) {
            try {
                //配置信息
                System.out.println("get lock...");
            } catch (Exception e) {
                System.out.println(" error ...");
            } finally {
                zkClient.unlockTimeVersion(path);
            }
        }

    }

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient();
        zkClient.init();

        LockThread lock1 = new LockThread(zkClient);
        LockThread lock2 = new LockThread(zkClient);

        Thread thread1 = new Thread(lock1);
        Thread thread2 = new Thread(lock2);

        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class LockThread implements Runnable {
    ZkClient zkClient = null;

    public LockThread(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void run() {
        String path = "/test_lock";
        boolean lock = zkClient.lock(path);
        if (lock) {
            try {
                //配置信息
                System.out.println(Thread.currentThread() + " : get lock...");
                Thread.sleep(10000);
            } catch (Exception e) {
                System.out.println(Thread.currentThread() +  " error ...");
            } finally {
                zkClient.unlock(path);
            }
        }
        System.out.println(Thread.currentThread() + " end ...");
    }
}