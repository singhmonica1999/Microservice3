package com.mooo.monicasingh.progresstracker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {

    // Define a Kafka topic for progress updates
    @Bean
    public NewTopic progressUpdatesTopic() {
        return new NewTopic("progress_updates", 1, (short) 1);
    }
}
