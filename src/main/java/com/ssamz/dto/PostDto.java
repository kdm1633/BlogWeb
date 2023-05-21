package com.ssamz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	@NotBlank(message = "The title is blank.")
	private String title;
	
	@NotBlank(message = "The content is blank.")
	private String content;
}
