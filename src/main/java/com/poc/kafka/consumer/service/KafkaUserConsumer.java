package com.poc.kafka.consumer.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.kafka.consumer.common.UserRole;
import com.poc.kafka.consumer.model.ConvertedUser;
import com.poc.kafka.consumer.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableKafka
@Slf4j
public class KafkaUserConsumer {

	@Autowired
	WebClient webClient;

	@KafkaListener(topics = "${spring.kafka.consumer.usertopic}", groupId = "spring.kafka.consumer.group-id")
	public void consumeUserMessage(String messages) throws JsonMappingException, JsonProcessingException {
		System.out.println(messages);

		ObjectMapper mapper = new ObjectMapper();
		User message = mapper.readValue(messages, User.class);
		UserRole role = UserRole.ADMIN_USER;
		if (message.getAge() < 20) {
			role = UserRole.CUSTOMER;
		}
		log.info(String.format("User message recieved -> %s", message.toString()));
		ConvertedUser converteduser = ConvertedUser.builder().id(message.getId()).name(message.getName())
				.address(message.getAddress()).age(message.getAge())
				.timeStamp(message.getId() + "" + message.getName() + "-" + new Timestamp(System.currentTimeMillis()))
				.userRole(role).build();

		webClient.post().uri("recieve_user_message").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.bodyValue(converteduser).retrieve().bodyToMono(String.class).block();
	}
}
