package com.rvg.kafka_example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    // This method listens to messages from the Kafka topic "myTopic"
    // The groupId "myGroupId" is used to identify the consumer group
    @KafkaListener(topics = "myTopic", groupId = "myGroupId")
    void listen(String data) {
        // Log the received message to the console
        System.out.println("Listener received " + data + "!!");
    }
}
