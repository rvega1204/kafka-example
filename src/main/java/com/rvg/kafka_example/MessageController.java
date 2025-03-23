package com.rvg.kafka_example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Import necessary classes for Spring Kafka and REST controller functionality
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    // KafkaTemplate is used to send messages to Kafka topics
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Constructor to initialize the KafkaTemplate
    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // This method is used to publish a message to the Kafka topic "myTopic"
    // The message is sent as a POST request to the endpoint "/api/v1/messages"
    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        if (request == null || request.message() == null || request.message().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        // Send the message to the Kafka topic "myTopic"
        kafkaTemplate.send("myTopic", request.message());
    }
}
