package com.connect.user.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.user.service.entity.UserEntity;
import com.connect.user.service.exception.UserException;
import com.connect.user.service.repository.UserRepository;
import com.connect.user.service.request.UserRequest;
import com.connect.user.service.response.AuthenticationResponse;
import com.connect.user.service.response.UserResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	public UserResponse findByEmailId(String emailId) {
		UserResponse userResponse = new UserResponse();

		List<UserEntity> userEntityList = userRepository.findByEmailId(emailId);

		userEntityList.stream().forEach(userData -> {
			userResponse.setEmailId(userData.getEmailId());
			userResponse.setRole(userData.getRole());
			userResponse.setPassword(userData.getPassword());
		});

		return userResponse;
	}

	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	public UserResponse saveUser(UserRequest userRequest) {
		UserResponse userResponse = new UserResponse();

		UserEntity userEntity = prepareServiceRequest(userRequest);

		Optional<UserEntity> serviceResponse = Optional
				.ofNullable(Optional.of(userRepository.save(userEntity)).orElseThrow(
						() -> new UserException("Exception while registering the user : " + userRequest.getEmailId())));

		if (serviceResponse.isPresent()) {
			userResponse.setEmailId(serviceResponse.get().getEmailId());
			userResponse.setRole(serviceResponse.get().getRole());
			userResponse.setPassword(serviceResponse.get().getPassword());
		}

		return userResponse;
	}

	/**
	 * 
	 * @param userRequest
	 * @return
	 */
	public AuthenticationResponse authenticate(UserRequest userRequest) {
		AuthenticationResponse authenticationResponse = null;

		Optional<UserEntity> userEntity = Optional.ofNullable(Optional
				.of(userRepository.findByEmailIdAndPasswordAndRole(userRequest.getEmailId(), userRequest.getPassword(),
						userRequest.getRole()))
				.orElseThrow(() -> new UserException(
						"Exception while authenticating the user : " + userRequest.getEmailId())));

		if (userEntity.isPresent()) {
			authenticationResponse = new AuthenticationResponse();
			authenticationResponse.setEmailId(userEntity.get().getEmailId());
			authenticationResponse.setRole(userEntity.get().getRole());
		}

		return authenticationResponse;
	}

	/**
	 * 
	 * 
	 * @param userRequest
	 * @return
	 */
	private UserEntity prepareServiceRequest(UserRequest userRequest) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmailId(userRequest.getEmailId());
		userEntity.setPassword(userRequest.getPassword());
		userEntity.setRole(userRequest.getRole());
		return userEntity;
	}

}
