package com.rvg.kafka_example.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/*
 * This class is used to configure the Kafka consumer properties
 * The consumerConfig() method is used to set the consumer properties
 * The consumerFactory() method is used to create a consumer factory
 * The factory() method is used to create a Kafka listener container factory
 * The Kafka listener container factory is used to create a Kafka listener container
 */
@Configuration
public class KafkaConsumerConfig {

    // Injects the Kafka bootstrap servers from the application properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /*
     * This method is used to set the consumer properties.
     * The consumer properties are configured using the ConsumerConfig class.
     * It includes bootstrap servers, key deserializer, value deserializer, and
     * other essential settings.
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // Disable auto-commit for better control
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Start reading from the earliest offset
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500); // Limit the number of records per poll
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000); // Set session timeout
        return props;
    }

    /*
     * This method is used to create a consumer factory
     * The consumer factory is created using the consumer properties
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /*
     * This method is used to create a Kafka listener container factory
     * The Kafka listener container factory is created using the consumer factory
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3); // Enable concurrent processing with 3 threads
        factory.getContainerProperties().setPollTimeout(3000L); // Set poll timeout to 3 seconds
        return factory;
    }
}
