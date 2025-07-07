package com.example.kafka.kafka_example.listener;

import org.springframework.kafka.annotation.KafkaHandler;

import com.example.kafka.kafka_example.dto.MessageDto;

//@Component
//@KafkaListener(id = "multiGroup", topics = Consumer.TOPIC)
public class MultiTypeKafkaListener {

	@KafkaHandler
	public void handleGreeting(final MessageDto message) {
		System.out.println("Message received in handler: " + message);
	}

	@KafkaHandler(isDefault = true)
	public void unknown(Object object) {
		System.out.println("Unkown type received: " + object);
	}
}
