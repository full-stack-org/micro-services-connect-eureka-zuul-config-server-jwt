package com.api.gateway.service.hystrix.fallback;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.api.gateway.service.proxy.AuthProxy;
import com.api.gateway.service.request.AuthRequest;
import com.api.gateway.service.request.LoadUserByEmailRequest;
import com.api.gateway.service.request.RegistrationRequest;
import com.api.gateway.service.response.AuthResponse;
import com.api.gateway.service.response.RegistrationResponse;
import com.api.gateway.service.response.StatusResponse;
import com.api.gateway.service.response.UserResponse;

@Component
public class HystirxFallBack implements AuthProxy {

	@Override
	public AuthResponse authenticateUser(AuthRequest authRequest) {
		AuthResponse authResponse = new AuthResponse();
		authResponse.setStatusResponse(getFallBackStatusVO());
		return authResponse;
	}

	@Override
	public UserResponse loadUserByEmailId(LoadUserByEmailRequest loadUserByEmailRequest) {
		UserResponse userResponse = new UserResponse();
		userResponse.setStatusResponse(getFallBackStatusVO());
		return userResponse;
	}

	@Override
	public RegistrationResponse registerUser(RegistrationRequest authRequest) {
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setStatusResponse(getFallBackStatusVO());
		return registrationResponse;
	}

	private StatusResponse getFallBackStatusVO() {
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatusCode("Failed");
		statusResponse.setStatusMessage(
				"CIRCUIT BREAKER ENABLED!!! No Response From User Details Service at this moment.  Service will be back shortly "
						+ LocalDateTime.now());
		return statusResponse;
	}

}
