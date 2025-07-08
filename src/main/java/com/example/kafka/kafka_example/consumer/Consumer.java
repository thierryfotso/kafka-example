package com.example.kafka.kafka_example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.example.kafka.kafka_example.dto.MessageDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Consumer {

	@KafkaListener(groupId = "group1", topicPartitions = @TopicPartition(topic = "${topics}", partitions = { "0",
			"1" }))
	public void consume(final ConsumerRecord<String, MessageDto> message,
			@Header(KafkaHeaders.RECEIVED_PARTITION) final int partition) {
		System.out.println("******message received:" + message);
		System.out.println("******partition:" + partition);
	}

	@KafkaListener(groupId = "group2", topicPartitions = @TopicPartition(topic = "${topics}", partitions = { "2" }))
	public void consumeGroup1(final MessageDto message, @Header("message-id") final String messageId,
			@Header(KafkaHeaders.RECEIVED_PARTITION) final int partition) {
		System.out.println("--------------message id:" + messageId);
		System.out.println("--------------message received:" + message);
		System.out.println("--------------partition:" + partition);
	}
}
