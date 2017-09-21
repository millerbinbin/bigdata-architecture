package com.jd.yhd.bigdata.redisTest;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
        try {
            Jedis conn = new BigDataConnection().getRedisConnection();
            conn.set("key_1", "1000");
            conn.setex("key_2", 2, "2000");
            conn.incrBy("key_1", 10);
            System.out.println(conn.get("key_1"));
            System.out.println(conn.get("key_2"));
            Thread.sleep(1500);
            System.out.println(conn.get("key_1"));
            conn.append("key_2", "append");
            System.out.println(conn.get("key_2"));
            System.out.println(conn.ttl("key_1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
