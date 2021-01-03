package com.producer.service.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNameRequest {
	
	@NotEmpty(message = "Name is Mandatory")
	private String name;
}
