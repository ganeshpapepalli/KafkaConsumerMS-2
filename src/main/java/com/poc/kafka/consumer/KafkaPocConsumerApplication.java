package com.poc.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaPocConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPocConsumerApplication.class, args);
	}

}
