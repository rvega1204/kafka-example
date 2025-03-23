package com.rvg.kafka_example.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/*
 * This class is used to configure the Kafka producer properties
 * The producerConfig() method is used to set the producer properties
 * The producerFactory() method is used to create a producer factory
 * The kafkaTemplate() method is used to create a Kafka template
 */
@Configuration
public class KafkaProducerConfig {

    // Injects the Kafka bootstrap servers from the application properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /*
     * This method is used to set the producer properties.
     * The producer properties are configured using the ProducerConfig class.
     * It includes bootstrap servers, key serializer, value serializer, and
     * other essential settings.
     */
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all"); // Ensures all replicas acknowledge
        props.put(ProducerConfig.RETRIES_CONFIG, 3); // Retry up to 3 times on failure
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // Adds a small delay to batch records
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // Sets batch size to 16KB
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy"); // Enables compression
        return props;
    }

    /*
     * This method is used to create a producer factory
     * The producer factory is created using the producer properties
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /*
     * This method is used to create a Kafka template
     * The Kafka template is created using the producer factory
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory());
    }
}
