package com.xiao.studydemos.thread;

import com.xiao.studydemos.pojo.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessManager {
    public static final Logger log = LoggerFactory.getLogger(ProcessManager.class);

    private int threadNum = 10;

    private ThreadPoolExecutor pool =null;
    private AtomicBoolean running = new AtomicBoolean(true);
    private List<ProcessThread> processThreadList = null;
    private List<Shop> shopList = null;


    public void stopProcess(){
        running.set(false);
    }

    public void startProcess(){
        running.set(true);
    }

    public void init(){
        processThreadList  = new ArrayList<>(threadNum);
        pool =(ThreadPoolExecutor) Executors.newFixedThreadPool(threadNum);
        for(int i=0;i<threadNum;i++){
            processThreadList.add(new ProcessThread());
        }
    }

    private void clear(){
        log.info("clear...");
        for(int i=0;i<threadNum;i++){
            processThreadList.get(i).stopProcess();
        }
    }

    public void setShopList(List<Shop> list){
        this.shopList = list;
    }

    public void start(){
        while (running.get()){
            clear();

            for(int i = 0;i<shopList.size();i++){
                int index = i % threadNum;
                ProcessThread pt = processThreadList.get(index);
                pt.addShop(shopList.get(i));
                pt.startProcess();
            }

            log.info("start...");
            for(int i=0;i<threadNum;i++){
                pool.execute(processThreadList.get(i));
            }

            try {
                Thread.sleep(5000);
                log.info("active={},task={},complete={}",pool.getActiveCount(),pool.getTaskCount(),pool.getCompletedTaskCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        log.info("stop manager...");
    }
}
