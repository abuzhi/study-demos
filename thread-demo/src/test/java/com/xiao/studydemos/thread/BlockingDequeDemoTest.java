package com.xiao.studydemos.thread;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang
 * 2018.02.12 11:03
 *
 * @Version 1.0
 */
public class BlockingDequeDemoTest {

    @Test
    public void sourceCode() throws Exception {
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        assertNotNull(linkedBlockingQueue);

        BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<String>(100);


    }
}