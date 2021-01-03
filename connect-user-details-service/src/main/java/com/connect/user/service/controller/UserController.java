package com.connect.user.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connect.user.service.exception.UserException;
import com.connect.user.service.jwt.token.provider.JWTUtil;
import com.connect.user.service.request.LoadByEmailIdRequest;
import com.connect.user.service.request.UserRequest;
import com.connect.user.service.response.AuthenticationResponse;
import com.connect.user.service.response.UserResponse;
import com.connect.user.service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user/v1")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;

	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/saveUser")
	public UserResponse saveUser(@Valid @RequestBody UserRequest userRequest) {
		log.info("Enter in saveUser of UserController email: {} ", userRequest.getEmailId());

		UserResponse serviceResponse = userService.findByEmailId(userRequest.getEmailId());

		if (StringUtils.hasText(serviceResponse.getEmailId())) {
			throw new UserException("User already exists in database " + userRequest.getEmailId());
		}

		UserResponse userResponse = userService.saveUser(userRequest);

		log.info("Exit in saveUser of UserController email: {} ", userResponse.getEmailId());
		return userResponse;
	}
	
	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/authenticate")
	public AuthenticationResponse authenticate(@Valid @RequestBody UserRequest userRequest) {
		log.info("Enter in authenticate of UserController email: {} ", userRequest.getEmailId());

		AuthenticationResponse userResponse = userService.authenticate(userRequest);
		
		String jwtToken = jwtUtil.generateToken(userRequest.getEmailId());
		
		if(StringUtils.hasText(jwtToken)) {
			userResponse.setJwtToken(jwtToken);
		}

		log.info("Exit in authenticate of UserController email: {} ", userResponse.getEmailId());
		return userResponse;
	}

	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/loadByEmailId")
	public UserResponse loadByEmailId(@Valid @RequestBody LoadByEmailIdRequest emailIdRequest) {
		log.info("Enter in loadByEmailId of UserController email: {} ", emailIdRequest.getEmailId());

		UserResponse userResponse = userService.findByEmailId(emailIdRequest.getEmailId());

		log.info("Exit in loadByEmailId of UserController email: {} ", userResponse.getEmailId());
		
		return userResponse;
	}
}
