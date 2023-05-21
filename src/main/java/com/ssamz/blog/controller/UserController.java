package com.ssamz.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.service.UserService;
import com.ssamz.dto.UserDto;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public @ResponseBody ResponseDto<String> signup(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		User user = modelMapper.map(userDto, User.class);
		User loginUser = userService.getUser(user);
		
		if (loginUser == null) {
			userService.signup(user);
			
			return new ResponseDto<String>(HttpStatus.OK.value(), user.getId() + " has been registered as a member.");
		}
		else
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), user.getId() + " already exists.");
	}
}
