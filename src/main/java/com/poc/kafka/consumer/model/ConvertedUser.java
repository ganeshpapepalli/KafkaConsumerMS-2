package com.poc.kafka.consumer.model;

import com.poc.kafka.consumer.common.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConvertedUser {
	private int id;
	private String name;
	private int age;
	private String address;
	private String timeStamp;
	private UserRole userRole;
}
