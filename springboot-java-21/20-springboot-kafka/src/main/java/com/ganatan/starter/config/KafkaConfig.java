package com.ganatan.starter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	public KafkaConfig() {
		System.out.println("00000000001:constructor:KafkaConfig");
	}

	@Bean
	public NewTopic testTopic() {
		return TopicBuilder.name("test-topic").partitions(1).replicas(1).build();
	}
}
