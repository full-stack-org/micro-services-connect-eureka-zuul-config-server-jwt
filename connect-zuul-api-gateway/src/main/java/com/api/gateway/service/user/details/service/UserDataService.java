package com.api.gateway.service.user.details.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.gateway.service.proxy.AuthProxy;
import com.api.gateway.service.request.LoadUserByEmailRequest;
import com.api.gateway.service.response.UserResponse;

@Service
public class UserDataService implements UserDetailsService {

	@Autowired
	private AuthProxy authProxy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LoadUserByEmailRequest loadUserByEmailRequest = new LoadUserByEmailRequest();
		loadUserByEmailRequest.setEmailId(username);

		UserResponse userResponse = authProxy.loadUserByEmailId(loadUserByEmailRequest);

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userResponse.getRole()));

		return new org.springframework.security.core.userdetails.User(userResponse.getEmailId(),
				userResponse.getPassword(), authorities);
	}

}
