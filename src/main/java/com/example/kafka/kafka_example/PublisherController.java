package com.example.kafka.kafka_example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

	private Producer producer;

	public PublisherController(final Producer producer) {
		this.producer = producer;
	}

	@PostMapping("/publish")
	public void publishMessage(@RequestParam("message") final String message) {
		this.producer.sendMessage(message);
	}
}
