# Kafka Example Project

This essential project demonstrates the use of Apache Kafka for building distributed messaging systems. It includes examples of producing, consuming, and processing messages using Kafka.

## Features

- Kafka Producer and Consumer implementation.
- Message serialization and deserialization.
- Example of Kafka Streams for real-time data processing.
- Configuration for local Kafka setup.

## Prerequisites

- Java 8 or higher
- Apache Kafka 4.0.0 installed locally or accessible remotely
- Maven or Gradle for dependency management
- Postman to send the POST request: http://localhost:8080/api/v1/messages

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/rvega1204/kafka-example.git
    cd kafka-example
    ```

2. Install dependencies:
    ```bash
    mvn install
    ```

3. Start Kafka server:
    ```bash
    ./bin/kafka-server-start.sh config/server.properties
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage

- **Producer**: Sends messages to a Kafka topic.
- **Consumer**: Reads messages from a Kafka topic.

## Configuration

Update the `application.properties` file to configure Kafka broker, topic names, and other settings.

## License

This project is licensed under the MIT License. It's free to update and modify, so enjoy!

## Acknowledgments

- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- Community tutorials and examples.
- Open-source contributors.
