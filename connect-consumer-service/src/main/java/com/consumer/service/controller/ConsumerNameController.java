package com.consumer.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.service.proxy.NameServiceProxy;
import com.consumer.service.request.NameRequest;
import com.consumer.service.response.NameResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/consumer/name/v1")
@Slf4j
public class ConsumerNameController {

	@Autowired
	private NameServiceProxy nameServiceProxy;

	@PostMapping("/getName")
	public NameResponse getName(@Valid @RequestBody NameRequest nameRequest) {
		log.info("Entered in getName of ConsumerNameController with input {} ", nameRequest.getName());
		NameResponse nameResponse = nameServiceProxy.getName(nameRequest);
		log.info("Exit in getName of ConsumerNameController with output {} ", nameResponse.getResponseMessage());
		return nameResponse;
	}

}
