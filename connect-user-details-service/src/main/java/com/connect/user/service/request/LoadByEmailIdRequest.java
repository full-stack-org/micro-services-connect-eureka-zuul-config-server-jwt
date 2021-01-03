package com.connect.user.service.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadByEmailIdRequest {
	@NotEmpty(message = "Email Id is Mandatory")
	private String emailId;

}
