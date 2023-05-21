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
	@NotBlank(message = "The user ID is blank.")
	@Size(min = 1, max = 20, message = "The user ID must be between 1 and 20 characters long.")
	private String userid;
	
	@NotBlank(message = "The email is blank.")
	@Email(message = "The email format is invalid.")
	private String email;
}
