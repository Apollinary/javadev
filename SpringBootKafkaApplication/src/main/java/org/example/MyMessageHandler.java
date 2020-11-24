package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
class MyMessageHandler implements MessageHandler {

    private final String topicTo = "Topic2";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MyMessageHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @ServiceActivator(inputChannel = "kafkaChannel")
    public void handleMessage(Message<?> message) throws MessagingException {
        String data = message.getPayload().toString();
        data = JSONProcessor.addTimeStamp(data);
        kafkaTemplate.send(topicTo, data);
    }
}
