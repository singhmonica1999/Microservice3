package com.mooo.monicasingh.progresstracker.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ProgressListener {

    @JmsListener(destination = "progress.queue")
    public void receiveProgressUpdate(String message) {
        System.out.println("Received progress update: " + message);
        // Process the message (e.g., update the database, trigger events, etc.)
    }
}
