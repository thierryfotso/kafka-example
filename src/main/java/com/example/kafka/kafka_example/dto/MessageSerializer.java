package com.example.kafka.kafka_example.dto;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageSerializer implements Serializer<MessageDto> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(final String topic, final MessageDto data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (final JsonProcessingException e) {
			throw new SerializationException(e);
		}
	}

}
