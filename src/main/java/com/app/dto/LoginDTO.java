package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	@NotEmpty(message = "Email can't be blank")
	@Email(message="Invalid Email")
	private	String email;
	@NotEmpty
	@Length(min = 3,max=20,message = "Invalid password length")
    private String password;
	
}
