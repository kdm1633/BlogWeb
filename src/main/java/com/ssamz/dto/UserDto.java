package com.ssamz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@NotBlank(message = "The username is blank.")
	@Size(min = 1, max = 20, message = "The username must be between 1 and 20 characters long.")
	private String username;
	
	@NotBlank(message = "The password is blank.")
	@Size(min = 1, max = 20, message = "The password must be between 8 and 20 characters long.")
	private String password;
	
	@NotBlank(message = "The email is blank.")
	@Email(message = "The email format is invalid.")
	private String email;
}
