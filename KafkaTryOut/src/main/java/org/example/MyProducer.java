package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {
    private int messageCounter;
    private final Producer<String, String> producer;

    public MyProducer() {
        Properties config = new Properties();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("acks", "all");
        config.put("retries", 0);
        config.put("batch.size", 16384);
        config.put("linger.ms", 1);
        config.put("buffer.memory", 33554432);
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(config);
    }

    public void sendMessageToTopic(String topicName, String messageText) {
        producer.send(new ProducerRecord<>(topicName, Integer.toString(messageCounter++), messageText));
        System.out.println("Message sent successfully");
    }

    public void close(){
        producer.close();
    }
}
