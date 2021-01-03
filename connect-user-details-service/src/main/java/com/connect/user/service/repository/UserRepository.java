package com.connect.user.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.user.service.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByEmailId(String emailId);

	UserEntity findByEmailIdAndPasswordAndRole(String emailId, String password, String role);

}
