package com.jd.yhd.bigdata.redis;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import redis.clients.jedis.Jedis;

/**
 * @author hubin6
 */
public class RedisStart {
    public static void main(String[] args) {
        try {
            Jedis conn = new BigDataConnection().getRedisConnection();
            conn.set("key_1", "1000");
            conn.setex("key_2", 2, "2000");
            conn.incrBy("key_1", 10);
            System.out.println("key_1: " + conn.get("key_1"));
            System.out.println("key_2: " + conn.get("key_2"));
            // Thread.sleep(2500);
            System.out.println("key_1: " + conn.get("key_1"));
            conn.append("key_2", "append");
            System.out.println("key_2: " + conn.get("key_2"));
            conn.sadd("key_3", "123");
            conn.sadd("key_3", "345");
            System.out.println("key_3: " + conn.smembers("key_3"));
            conn.zadd("key_4", 0.2, "test1");
            conn.zadd("key_4", 0.3, "test2");
            conn.zadd("key_4", 0.24, "test3");
            conn.zadd("key_4", 0.15, "test4");
            System.out.println("key_4: " + conn.zrangeByScore("key_4", 0, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
