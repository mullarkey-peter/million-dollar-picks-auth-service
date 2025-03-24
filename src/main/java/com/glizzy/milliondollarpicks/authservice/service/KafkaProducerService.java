package com.glizzy.milliondollarpicks.authservice.service;

import com.glizzy.milliondollarpicks.authservice.event.LoginEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.login-events:login-events}")
    private String loginEventsTopic;

    public void publishLoginEvent(String username) {
        LoginEvent event = new LoginEvent(username, System.currentTimeMillis());
        log.info("Publishing login event for user: {}", username);
        kafkaTemplate.send(loginEventsTopic, username, event);
    }
}