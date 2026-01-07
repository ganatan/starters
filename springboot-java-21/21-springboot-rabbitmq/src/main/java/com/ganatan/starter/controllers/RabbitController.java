package com.ganatan.starter.controllers;

import com.ganatan.starter.config.RabbitConfig;
import com.ganatan.starter.messaging.RabbitProducer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    private final RabbitProducer producer;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;

    public RabbitController(RabbitProducer producer, RabbitTemplate rabbitTemplate, RabbitConfig rabbitConfig) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
    }

    @GetMapping("/send")
    public String send(@RequestParam String msg) {
        producer.send(msg);
        return "Message envoyé (simple) : " + msg;
    }

    @PostMapping("/sendEvent")
    public String sendEvent(
            @RequestParam String event,
            @RequestParam Long userId,
            @RequestParam String username,
            @RequestParam String email
    ) {
        producer.sendUserCreatedEvent(userId, username, email, event);
        return "Message envoyé (event) : " + event + " pour user " + username;
    }

    @GetMapping("/receive")
    public String receive() {
        Message message = rabbitTemplate.receive(rabbitConfig.getQueueName());
        if (message != null) {
            return "📥 Message lu : " + new String(message.getBody());
        }
        return "❌ Aucun message disponible dans la queue";
    }
}
