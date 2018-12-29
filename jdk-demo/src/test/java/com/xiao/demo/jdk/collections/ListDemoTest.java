package com.xiao.demo.jdk.collections;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang on 2017/5/21.
 */
public class ListDemoTest {
    private RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z').build();
    private long startTime = 1526834625000L;
    private long endTime = 1527093825000L;


    @Test
    public void testArrayList(){
        List<String> list = new ArrayList<>();

        list.add(0,"0");

        list.add("test");

        System.out.println(Arrays.toString(list.toArray()));

    }

    @Test
    public void testLinkedList(){
        List<String> list = new LinkedList<>();

    }

    @Test
    public void testVector() throws Exception {
        List<String> v = new Vector<>(100);

    }

    @Test
    public void testListCopy() {
        List<Map> list = new ArrayList();
        for(int i=0;i<5;i++){
            list.add(getObj());
        }

        for(Map map : list){
            map.put("id","123");
        }

        assertNotNull(list);
    }

    private Map<String, Object> getObj() {
        Map<String, Object> map = new HashMap<>();
        DateTime d = new DateTime(RandomUtils.nextLong(startTime, endTime));
        String day = d.toString("yyyy-MM-dd");
        String time = d.toString("yyyy-MM-dd HH:mm:ss");
        map.put("topic_id", RandomUtils.nextInt(1, 10));
        map.put("type", "Topic.Time");
        map.put("name", generator.generate(1));
        map.put("mt_time", time);
        map.put("day", day);
        map.put("avg", RandomUtils.nextDouble(0, 1000));
        map.put("count", RandomUtils.nextInt(0, 10000));
        map.put("failCount", RandomUtils.nextInt(0, 10000));
        map.put("failPercent", RandomUtils.nextDouble(0, 100));
        map.put("value_id", generator.generate(8));
        map.put("max", RandomUtils.nextInt(0, 10000));
        map.put("successPercent", RandomUtils.nextDouble(0, 100));
        map.put("sum", RandomUtils.nextInt(0, 10000));
        map.put("tp90", RandomUtils.nextDouble(90, 100));
        map.put("tp95", RandomUtils.nextDouble(95, 100));
        map.put("tp99", RandomUtils.nextDouble(99, 100));
        map.put("tp999", RandomUtils.nextDouble(99, 100));


        return map;
    }

}