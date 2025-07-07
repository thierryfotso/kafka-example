package com.example.kafka.kafka_example;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafka.kafka_example.dto.MessageDto;

@Service
public class Producer {

	private static final String TOPIC = "test_topic";

	@Autowired
	private KafkaTemplate<String, MessageDto> kafkaTemplate;

	public void sendMessage(final String content) {
		System.out.println("**********message to send:" + content);
		final UUID key = UUID.randomUUID();
		final MessageDto payload = new MessageDto(content);
		final ProducerRecord<String, MessageDto> message = new ProducerRecord<>(TOPIC, key.toString(), payload);
		message.headers().add("message-id", UUID.randomUUID().toString().getBytes());

		final CompletableFuture<SendResult<String, MessageDto>> sendResult = this.kafkaTemplate.send(message);
		sendResult.whenComplete((result, ex) -> {
			System.out.println("send result:" + result);
		});
	}
}
