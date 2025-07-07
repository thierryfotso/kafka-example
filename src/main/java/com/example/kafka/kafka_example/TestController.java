package com.example.kafka.kafka_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private final Consumer consumer;

	private Producer producer;

	@Autowired
	public TestController(final Producer producer, Consumer consumer) {
		this.producer = producer;
		this.consumer = consumer;
	}

	@PostMapping("/publish")
	public void publishMessage(@RequestParam("message") final String message) {
		this.producer.sendMessage(message);
	}
}
