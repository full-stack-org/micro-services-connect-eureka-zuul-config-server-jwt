package com.api.gateway.service.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gateway.service.exception.UserException;
import com.api.gateway.service.proxy.AuthProxy;
import com.api.gateway.service.request.AuthRequest;
import com.api.gateway.service.request.RegistrationRequest;
import com.api.gateway.service.response.AuthResponse;
import com.api.gateway.service.response.RegistrationResponse;
import com.api.gateway.service.response.StatusResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gateway/user/v1")
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthProxy authProxy;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param authRequest
	 * @return
	 */
	@PostMapping("/authnticate")
	@HystrixCommand(fallbackMethod = "authenticateFallBack")
	public AuthResponse authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
		log.info("Entered authenticateUser in AuthenticationController email Id {} ", authRequest.getEmailId());
		AuthResponse authResponse = new AuthResponse();
		try {
			authResponse = authProxy.authenticateUser(authRequest);
		} catch (Exception e) {
			throw new UserException("User not found for user id " + authRequest.getEmailId());
		}

		log.info("Exit authenticateUser in AuthenticationController email Id {} ", authResponse.getEmailId());

		return authResponse;
	}

	/**
	 * 
	 * @param authRequest
	 * @return
	 */
	@SuppressWarnings("unused")
	private AuthResponse authenticateFallBack(AuthRequest authRequest) {

		AuthResponse authResponse = new AuthResponse();

		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatusCode("Failed");
		statusResponse.setStatusMessage(
				"CIRCUIT BREAKER ENABLED!!! No Response From User Details Service at this moment.  Service will be back shortly "
						+ LocalDateTime.now());

		authResponse.setStatusResponse(statusResponse);

		return authResponse;
	}

	/**
	 * 
	 * @param registrationRequest
	 * @return
	 */
	@PostMapping("/registerUser")
	@HystrixCommand(fallbackMethod = "registerUserFallBack")
	public RegistrationResponse registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
		log.info("Entered registerUser in AuthenticationController email Id {} ", registrationRequest.getEmailId());
		RegistrationResponse registrationResponse = new RegistrationResponse();
		try {
			registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
			registrationResponse = authProxy.registerUser(registrationRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("User Al ready exits in Database " + registrationRequest.getEmailId());
		}

		log.info("Exit registerUser in AuthenticationController email Id {} ", registrationResponse.getEmailId());

		return registrationResponse;
	}

	/**
	 * 
	 * @param authRequest
	 * @return
	 */
	@SuppressWarnings("unused")
	private RegistrationResponse registerUserFallBack(RegistrationRequest registrationRequest) {

		RegistrationResponse registrationResponse = new RegistrationResponse();

		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatusCode("Failed");
		statusResponse.setStatusMessage(
				"CIRCUIT BREAKER ENABLED!!! No Response From User Details Service at this moment.  Service will be back shortly "
						+ LocalDateTime.now());

		registrationResponse.setStatusResponse(statusResponse);

		return registrationResponse;
	}

}
