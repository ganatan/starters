package com.ganatan.starter.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@ConditionalOnProperty(
    value = "app.rabbitmq.consumer.enabled",
    havingValue = "true",
    matchIfMissing = true
)
public class RabbitConsumer {

    @RabbitListener(queues = "#{@rabbitConfig.getQueueName()}")
    public void receive(Object message) {
        if (message instanceof String text) {
            System.out.println("📥 Message texte reçu : " + text);
        } else if (message instanceof Map<?, ?> json) {
            System.out.println("📥 Message JSON reçu : " + json);
        } else {
            System.out.println("❌ Type de message inconnu : " + message);
        }
    }
}
