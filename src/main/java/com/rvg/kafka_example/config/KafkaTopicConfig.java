package com.rvg.kafka_example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/*
 * This class is used to create a Kafka topic
 * The myTopic() method is used to create a new topic
 */
@Configuration
public class KafkaTopicConfig {

    // This method is used to create a new Kafka topic named "myTopic"
    @Bean
    public NewTopic myTopic() {
        return TopicBuilder.name("myTopic")
                .build();
    }
}
