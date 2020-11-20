package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class KafkaSenderExample {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSenderExample(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
        System.out.println("Sent successfully");
    }

}
