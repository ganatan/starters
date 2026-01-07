package com.ganatan.starter.controllers;

import com.ganatan.starter.messaging.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	private final KafkaProducer producer;

	public KafkaController(KafkaProducer producer) {
		this.producer = producer;
	}

	@PostMapping("/send")
	public String send(@RequestParam String message) {
		producer.sendMessage("test-topic", message);
		return "Message envoyé : " + message;
	}
}
