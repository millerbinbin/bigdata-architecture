package com.jd.yhd.bigdata.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hubin6
 */
public class Constants {
    public final static String BIGDATA_HOST = "192.168.99.100";
    public final static int MONGO_PORT = 27017;
    public final static String MONGO_USERNAME = "mongo_user";
    public final static char[] MONGO_PASSWORD = "mongo_user".toCharArray();
    public final static String MONGO_DATABASE = "test";
    public final static int REDIS_PORT = 6379;
    public final static String KAFKA_PORTS = "32789,32790";
    public final static int ZOOKEEPER_PORTS = 49181;
    public final static String KAFKA_SERVER = getServers(KAFKA_PORTS);
    public final static String ZOOKEEPER_SERVER = getServers("" + ZOOKEEPER_PORTS);

    private static String getServers(String portStr) {
        List<String> list = new ArrayList<String>();
        Arrays.stream(("" + portStr).split(",")).forEach(port -> list.add(BIGDATA_HOST + ":" + port));
        return String.join(",", list);
    }
}
