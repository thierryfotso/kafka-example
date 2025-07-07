package com.example.kafka.kafka_example;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafka.kafka_example.dto.MessageDto;

@Service
public class Consumer {

	public static final String TOPIC = "test_topic";

	@KafkaListener(topics = TOPIC, groupId = "group_id")
	public void consume(final ConsumerRecord<String, MessageDto> consumerRecord) {
		System.out.println("--------------message received:" + consumerRecord);
		final MessageDto message = consumerRecord.value();
		System.out.println("Consumed Message" + message);
	}
}
