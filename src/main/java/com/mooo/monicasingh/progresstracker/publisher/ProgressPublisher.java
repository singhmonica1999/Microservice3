package com.mooo.monicasingh.progresstracker.publisher;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProgressPublisher {

    private final JmsTemplate jmsTemplate;

    public ProgressPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendProgressUpdate(String userId, String message) {
        jmsTemplate.convertAndSend("progress.queue", userId + ": " + message);
    }
}

