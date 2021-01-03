package com.consumer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.consumer.service")
@EnableEurekaClient
public class ConnectConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectConsumerServiceApplication.class, args);
	}

}
