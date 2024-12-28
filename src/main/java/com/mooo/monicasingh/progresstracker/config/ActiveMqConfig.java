package com.mooo.monicasingh.progresstracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.apache.activemq.command.ActiveMQQueue;

import jakarta.jms.Queue;

@Configuration
@EnableJms
public class ActiveMqConfig {

    @Bean
    public Queue progressQueue() {
        return new ActiveMQQueue("progress.queue");
    }
}
