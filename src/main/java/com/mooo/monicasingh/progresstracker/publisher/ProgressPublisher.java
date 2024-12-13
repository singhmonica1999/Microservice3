package com.mooo.monicasingh.progresstracker.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProgressPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProgressPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishProgressUpdate(String userId, String message) {
        kafkaTemplate.send("progress_updates", userId, message);
    }
}

