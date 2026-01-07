package com.ganatan.starter.messaging;

import com.ganatan.starter.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;

    public RabbitProducer(RabbitTemplate rabbitTemplate, RabbitConfig rabbitConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(
                rabbitConfig.getExchangeName(),
                rabbitConfig.getRoutingKey(),
                message
        );
        System.out.println("📤 Message simple envoyé : " + message);
    }

    public void sendUserCreatedEvent(Long userId, String username, String email, String event) {
        Map<String, Object> message = new HashMap<>();
        message.put("uuid", UUID.randomUUID().toString());
        message.put("timestamp", Instant.now().toString());
        message.put("service", "springboot-backend");
        message.put("event", event);

        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);
        payload.put("username", username);
        payload.put("email", email);

        message.put("payload", payload);

        rabbitTemplate.convertAndSend(
                rabbitConfig.getExchangeName(),
                rabbitConfig.getRoutingKey(),
                message
        );

        System.out.println("📤 Message enrichi envoyé : " + message);
    }
}
