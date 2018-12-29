package com.xiao.demo.lambda.collections;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.beans.binding.MapBinding;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2018/10/18.
 */
public class ListDemoTest {

    @Test
    public void test() throws Exception {
        List<String> list = Collections.emptyList();
        List<Integer> li = Collections.emptyList();
        Map<String, Integer> m1 = new HashMap<>();
        Map<Integer, String> m2 = new HashMap<>();

//        int sum = 0;
//        list.forEach(e -> { sum += e.size(); }); // Illegal, close over values

        List<String> aList = new ArrayList<>();
        list.forEach(x -> {
            aList.add(x);
        }); // Legal, open over variables

        int sum =
                list.stream()
                        .mapToInt(e -> e.length())
                        .sum();

        long sum2 = list.parallelStream().mapToLong(e -> e.length()).count();
        sum =
                list.stream()
                        .mapToInt(e -> e.length())
                        .reduce(0, (x, y) -> x + y);

        List<Integer> r = list.stream()
                .map(e -> new Integer(e))
                .filter(e -> e > 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctPrimary result is: " + r);
    }

    public int add(int x, int y) {
        return x + y;
    }


    @Test
    public void testDemo() throws Exception {
        String responseString = "{\"code\":0,\"message\":\"ok\",\"data\":{\"items\":[{\"eventId\":1789150,\"eventName\":\"test1\",\"eventType\":\"view\"}," +
                "{\"eventId\":343186,\"eventName\":\"test2\",\"eventType\":\"view\"}," +
                "{\"eventId\":15415,\"eventName\":\"test3\",\"eventType\":\"view\"}]}}";

        JSONObject object = JSONObject.parseObject(responseString);
        Info tmp = JSONObject.parseObject("{\"eventId\":15415,\"eventName\":\"test3\",\"eventType\":\"view\"}",Info.class);

//        Map<String,Info> map = object.getJSONObject("data")
//                .getJSONArray("items")
//                .stream().map(str -> JSONObject.parseObject(String.valueOf(str),Info.class))
//                ;
//        assertNotNull(map);
    }



}


class Info {
    private Integer eventId;
    private String eventName;
    private String eventType;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
