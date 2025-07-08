package com.example.kafka.kafka_example;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.example.kafka.kafka_example.dto.MessageDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@KafkaListener(topics = { "${topics}" }, groupId = "group_id")
public class Consumer {

	@KafkaHandler
	public void consume(final MessageDto message, @Header("message-id") final String messageId) {
		log.info("--------------message id:" + messageId);
		log.info("--------------message received:" + message);
		System.out.println("--------------message received:" + message);
	}
}
