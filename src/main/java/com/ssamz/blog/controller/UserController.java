package com.ssamz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public @ResponseBody ResponseDto<String> signup(@RequestBody User user) {
		if (userService.existentId(user)) 
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), user.getId() + " already exists.");
		
		userService.signup(user);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), user.getId() + " has been registered as a member.");
	}
}
