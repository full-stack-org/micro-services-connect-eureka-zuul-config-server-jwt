package com.api.gateway.service.proxy;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.gateway.service.hystrix.fallback.HystirxFallBack;
import com.api.gateway.service.request.AuthRequest;
import com.api.gateway.service.request.LoadUserByEmailRequest;
import com.api.gateway.service.request.RegistrationRequest;
import com.api.gateway.service.response.AuthResponse;
import com.api.gateway.service.response.RegistrationResponse;
import com.api.gateway.service.response.UserResponse;

@FeignClient(name = "connect-user-details-service",fallback = HystirxFallBack.class)
public interface AuthProxy {

	@PostMapping("/user/v1/authenticate")
	public AuthResponse authenticateUser(@Valid @RequestBody AuthRequest authRequest);
	
	@PostMapping("/user/v1/loadByEmailId")
	public UserResponse loadUserByEmailId(@Valid @RequestBody LoadUserByEmailRequest loadUserByEmailRequest);
	
	@PostMapping("/user/v1/saveUser")
	public RegistrationResponse registerUser(@Valid @RequestBody RegistrationRequest authRequest);
}
