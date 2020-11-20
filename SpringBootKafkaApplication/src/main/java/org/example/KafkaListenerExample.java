package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {

    private final String topicFrom = "Topic2";
    private final String topicTo = "Topic3";

    @Autowired
    private KafkaSenderExample kafkaSenderExample;

    public KafkaListenerExample(KafkaSenderExample kafkaSenderExample) {
        this.kafkaSenderExample = kafkaSenderExample;
    }

    @KafkaListener(topics = topicFrom)
    public void listener(String data, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        data = JSONProcessor.addTimeStamp(data, ts);
        kafkaSenderExample.sendMessage(topicTo, data);
    }
}