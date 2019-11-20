package com.xiao.studydemos.thread;

import com.xiao.studydemos.pojo.Shop;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProcessManagerTest {

    @Test
    public void start() throws Exception {
        List<Shop> shopList = new ArrayList<>();
        for(int i=0;i<100;i++){
            shopList.add(new Shop(i,"test_" +i));
        }
        ProcessManager manager = new ProcessManager();
        manager.init();
        manager.setShopList(shopList);
        manager.start();

        Thread.sleep(60000);
        System.out.println("test end...");
    }
}