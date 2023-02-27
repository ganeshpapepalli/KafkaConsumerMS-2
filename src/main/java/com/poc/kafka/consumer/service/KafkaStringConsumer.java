package com.poc.kafka.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableKafka
@Slf4j
public class KafkaStringConsumer {

	@Autowired
	WebClient webClient;

	@KafkaListener(topics = { "stringtopic" })
	public void consume(String message) {

		log.info(String.format("message recieved -> %s", message.toString()));

		webClient.get().uri("recieve_string_message/"+message).retrieve().bodyToMono(String.class).block();
		
	}
	

}
