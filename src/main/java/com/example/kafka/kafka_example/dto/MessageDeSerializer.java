package com.example.kafka.kafka_example.dto;

import java.io.IOException;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDeSerializer implements Deserializer<MessageDto> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public MessageDto deserialize(final String topic, final byte[] data) {
		try {
			return objectMapper.readValue(data, MessageDto.class);
		} catch (final IOException e) {
			throw new SerializationException(e);
		}
	}

}
