package com.connect.user.service.global.exception.handler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {
	private String message;
	private LocalDateTime localDateTime;
	private String details;
}
