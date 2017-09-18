package kafkaTest.consumer;

public class C1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new KafkaConsumerTest("t-1", "jd-group1"));
        Thread t2 = new Thread(new KafkaConsumerTest("t-2","jd-group1"));
        Thread t3 = new Thread(new KafkaConsumerTest("t-3","jd-group2"));
        t1.start();
        t2.start();
        t3.start();
    }
}
