package com.mooo.monicasingh.progresstracker.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProgressListener {

    @KafkaListener(topics = "progress_updates", groupId = "progress-tracker-group")
    public void handleProgressUpdate(String message) {
        System.out.println("Received progress update: " + message);
        // Process the message (e.g., update database, trigger events, etc.)
    }
}
