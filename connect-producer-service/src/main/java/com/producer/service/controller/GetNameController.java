package com.producer.service.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.service.request.GetNameRequest;
import com.producer.service.response.GetNameResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/provider/name/v1")
@Slf4j
public class GetNameController {

	@PostMapping("/getName")
	public GetNameResponse getName(@Valid @RequestBody GetNameRequest nameRequest) {
		log.info("Entered in getName of GetNameController with input {} ", nameRequest.getName());
		GetNameResponse getNameResponse = new GetNameResponse();
		getNameResponse.setResponseMessage("Hello " + nameRequest.getName());
		log.info("EExit in getName of GetNameController with output {} ", getNameResponse.getResponseMessage());
		return getNameResponse;
	}

}
