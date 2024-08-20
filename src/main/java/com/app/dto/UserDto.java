package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String firstName; //VARCHAR(25)
	private String lastName ;//VARCHAR(25)
	private String email ;//VARCHAR(50)
	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password ;//VARCHAR(200)
	private String address ;//VARCHAR(200)
	private String phone ;//VARCHAR(15)
	
   
}
