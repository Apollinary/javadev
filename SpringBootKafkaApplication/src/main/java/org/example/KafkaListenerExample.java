package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@EnableKafka
@SpringBootApplication
public class KafkaListenerExample {

    final String topicFrom = "Topic2";
    final String topicTo = "Topic3";

    @Autowired
    KafkaSenderExample kafkaSenderExample;

    public KafkaListenerExample(KafkaSenderExample kafkaSenderExample) {
        this.kafkaSenderExample = kafkaSenderExample;
    }

    @KafkaListener(topics = topicFrom)
    public void listener(String data, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        data = JSONProcessor.addTimeStamp(data, ts);
        kafkaSenderExample.sendMessage(topicTo, data);
    }
}