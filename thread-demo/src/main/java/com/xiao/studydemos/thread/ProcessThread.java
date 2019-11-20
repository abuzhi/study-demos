package com.xiao.studydemos.thread;

import com.xiao.studydemos.pojo.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ProcessThread.class);

    private AtomicBoolean running = new AtomicBoolean(true);
    private List<Shop> shopList = new ArrayList<>();
    private ConcurrentLinkedQueue<Shop> queue = new ConcurrentLinkedQueue<>();
    private int count = 0;

    public void stopProcess(){
        log.info("stop....");
        running.set(false);
    }

    public void startProcess(){
        running.set(true);
    }

    public void addShop(Shop shop){
        this.shopList.add(shop);
        this.queue.add(shop);
    }

    @Override
    public void run() {
        while (running.get() && !this.queue.isEmpty()){
            Shop shop = queue.poll();
            try {
                count ++;
//                log.info(Thread.currentThread().getId() + "count={},shop={}",count,shop.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!running.get()){
            log.info("clear queue...");
            queue.clear();
        }
        log.info(Thread.currentThread().getId() + " end run ...count = " + count);
    }
}
