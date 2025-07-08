package com.example.kafka.kafka_example.producer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafka.kafka_example.dto.MessageDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Producer {

	@Value("${topics}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, MessageDto> kafkaTemplate;

	public void sendMessage(final String content) {
		System.out.println("**********message to send:" + content);
		final UUID key = UUID.randomUUID();
		final MessageDto payload = new MessageDto(content);
		ProducerRecord<String, MessageDto> message0 = new ProducerRecord<>(topic, 0, key.toString(), payload);
		message0.headers().add("message-id", UUID.randomUUID().toString().getBytes());

		final CompletableFuture<SendResult<String, MessageDto>> sendResult = this.kafkaTemplate.send(message0);
		sendResult.whenComplete((result, ex) -> {
			if (ex != null) {
				log.error("An error occured while sending message:", ex);
			} else {
				System.out.println("send result:" + result);
			}
		});

		final ProducerRecord<String, MessageDto> message1 = new ProducerRecord<>(topic, 1, key.toString(), payload);
		message1.headers().add("message-id", UUID.randomUUID().toString().getBytes());
		this.kafkaTemplate.send(message1);

		final ProducerRecord<String, MessageDto> message2 = new ProducerRecord<>(topic, 2, key.toString(), payload);
		message2.headers().add("message-id", UUID.randomUUID().toString().getBytes());
		this.kafkaTemplate.send(message2);
	}
}
