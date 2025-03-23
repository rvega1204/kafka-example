package com.rvg.kafka_example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

// Importing necessary classes for Kafka configuration and usage
@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	// CommandLineRunner is used to run the KafkaTemplate to send a message to the
	// topic
	// The message is sent to the topic "myTopic"
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			kafkaTemplate.send("myTopic", "Hello, Kafka :)");
			System.out.println("Sent message='Hello, Kafka!' to topic='myTopic'");
		};
	}

}
