package com.ganatan.starter.messaging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.kafka.consumer.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumer {
	
	public KafkaConsumer() {
		System.out.println("00000000001:constructor:KafkaConsumer");
	}

	@KafkaListener(topics = "test-topic", groupId = "ganatan-group")
	public void listen(String message) {
		System.out.println("📩 Message reçu : " + message);
	}
}
