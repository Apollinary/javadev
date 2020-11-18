package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

public class ClientTryOut {
    MyConsumer myConsumer;
    MyProducer muProducer;

    public ClientTryOut() {
        myConsumer = new MyConsumer();
        muProducer = new MyProducer();
    }

    public void startApp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("The application is started!\nEnter the topicName to create topic or \"exit\" to stop application:");
        String messageText;
        String topicName = scanner.nextLine();

        if (!topicName.equals("exit")) {
            createTopic(topicName);
            myConsumer.subscribe(topicName);
            while (true) {
                System.out.print("Enter the message text or \"exit\" to stop application: ");
                messageText = scanner.nextLine();
                if (messageText.equals("exit")) {
                    break;
                }

                muProducer.sendMessageToTopic(topicName, messageText);
                myConsumer.readMessageFromTopic();
            }

        }
        System.out.println("The application is shut down.");
        System.exit(0);

        muProducer.close();
    }

    public void createTopic(String topicName) {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient admin = AdminClient.create(config);
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        admin.createTopics(Collections.singleton(newTopic));
    }

}