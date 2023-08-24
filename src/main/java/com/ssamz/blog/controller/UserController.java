package com.ssamz.blog.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.OauthType;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.security.UserDetailsImpl;
import com.ssamz.blog.service.UserService;
import com.ssamz.dto.UserDto;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public @ResponseBody ResponseDto<String> signup(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		User user = modelMapper.map(userDto, User.class);
		
		if (!userService.hasUsername(user)) {
			userService.signup(user);
			
			return new ResponseDto<String>(HttpStatus.OK.value(), user.getUsername() + " has been registered as a member.");
		}
		else
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + " already exists.");
	}
	
	@GetMapping("/login")
	public String login() {
		return "system/login";
	}
	
	@GetMapping("/user/detail")
	public String getUserDetail() {
		return "user/detail";
	}
	
	@PutMapping("/user")
	public @ResponseBody ResponseDto<String> updateUser(@RequestBody User user, @AuthenticationPrincipal UserDetailsImpl principal) {
		if (principal.getUser().getOauth().equals(OauthType.KAKAO))
			user.setPassword(kakaoPassword);
		
		principal.setUser(userService.updateUser(user));
		
		return new ResponseDto<>(HttpStatus.OK.value(), user.getUsername() + " is updated.");
	}
}
