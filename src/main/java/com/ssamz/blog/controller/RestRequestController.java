package com.ssamz.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssamz.blog.domain.User;

@RestController
public class RestRequestController {
	@GetMapping("/blog")
	public User httpGet() {
		User user = User.builder()
				.num(1)
				.username("gurum")
				.password("222")
				.email("gurum@gmail.com")
				.build();
		
		return user;
	}
	
	@PostMapping("/blog")
	public String httpPost(@RequestBody User user) {
		return "POST request input: " + user.toString();
	}
	
	@PutMapping("/blog")
	public String httpPut(@RequestBody User user) {
		return "PUT request input: " + user.toString();
	}
	
	@DeleteMapping("/blog")
	public String httpDelete(@RequestParam int id) {
		return "DELETE request input " + id;
	}
}
