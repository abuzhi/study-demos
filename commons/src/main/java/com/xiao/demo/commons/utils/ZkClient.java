package com.xiao.demo.commons.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class ZkClient {
    public static final Logger log = LoggerFactory.getLogger(ZkClient.class);

    public static final String ZOOKEEPER_LOCK_PATH = "/locks";
    private String zkServer = "127.0.0.1:2181";
    private String namespace = "armada";

    private CuratorFramework curator;

    public void init() {
        curator = CuratorFrameworkFactory
                .builder()
                .connectString(zkServer)
                .namespace(namespace)
                .retryPolicy(new RetryNTimes(1000, 1000))
                .build();
        curator.start();
    }

    public void setData(String path, String data) throws Exception {
        Stat stat = curator.checkExists().forPath(path);
        if (stat == null) {
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, data.getBytes());
            log.info(path + " create success. data:" + data);
        } else {
            curator.setData().forPath(path, data.getBytes());
            log.warn(path + " has bean updated.");
        }
    }

    /**
     * 获取排它锁
     */
    public boolean lock(String path) {
        try {
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(ZOOKEEPER_LOCK_PATH + path);
            log.info("获得锁成功。path:" + path);
            return true;
        } catch (Exception e) {
            log.error("获得锁失败");
        }
        return false;
    }

    /**
     * 获取排它锁
     *
     * @return
     */
    public boolean lock() {

        try {
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(ZOOKEEPER_LOCK_PATH + "/default");
            log.info("获得锁成功");
            return true;
        } catch (Exception e) {
            log.error("获得锁失败");
        }
        return false;
    }

    /**
     * @param path
     * @param timeVersion
     * @return
     */
    public boolean lockWithTimeVersion(String path, String timeVersion) {
        String versionPath = ZOOKEEPER_LOCK_PATH + path + "/version";
        String lockPath = ZOOKEEPER_LOCK_PATH + path + "/lock";
        boolean lock = false;

        //创建锁，排除同时竞争
        try {
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(lockPath);
            lock = true;
        } catch (Exception e) {
            log.error("获得锁失败");
            lock = false;
        }

        //业务需求，判断时间段内是否重复用锁
        if (lock) {
            try {
                Stat stat = curator.checkExists().forPath(versionPath);
                if (stat == null) {
                    curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(versionPath, timeVersion.getBytes());
                    log.info(path + " create success. data:" + timeVersion);
                    return true;
                } else {
                    //如果在同一时间段内，则返回false
                    String oldTime = this.getData(versionPath);
                    if(oldTime.equalsIgnoreCase(timeVersion)){
                        return false;
                    }
                    curator.setData().forPath(versionPath, timeVersion.getBytes());
                    log.warn(versionPath + " has bean updated."+ timeVersion);
                }
            } catch (Exception e) {
                log.error("获取version失败");
                lock = false;
            }
        }
        return lock;
    }


    /**
     * 释放排它锁
     *
     * @param path
     */
    public void unlock(String path) {
        try {
            delete(ZOOKEEPER_LOCK_PATH + path);
        } catch (Exception e) {
            log.error("删除节点失败.path:" + path, e);
        }
    }

    public void unlockTimeVersion(String path) {
        try {
            delete(ZOOKEEPER_LOCK_PATH + path + "/lock");
        } catch (Exception e) {
            log.error("删除节点失败.path:" + path, e);
        }
    }

    /**
     * 释放排它锁
     */
    public void unlock() {
        try {
            delete(ZOOKEEPER_LOCK_PATH);
        } catch (Exception e) {
            log.error("删除节点失败");
        }
    }

    public void delete(String path) throws Exception {
        Stat stat = curator.checkExists().forPath(path);
        if (stat != null) {
            curator.delete().deletingChildrenIfNeeded().forPath(path);
            log.info(path + "delete success.");
        } else {
            log.warn(path + " not exist.");
        }
    }


    public String getData(String path) throws Exception {
        byte[] buffer = curator.getData().forPath(path);
        if (buffer == null || buffer.length == 0) {
            return "";
        }
        return new String(buffer, Charset.forName("utf-8"));
    }


}
