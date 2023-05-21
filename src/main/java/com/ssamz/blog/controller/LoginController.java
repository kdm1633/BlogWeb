package com.ssamz.blog.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.service.UserService;
import com.ssamz.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/login")
	public String login() {
		return "system/login";
	}
	
	@PostMapping("/login")
	public @ResponseBody ResponseDto<String> login(@RequestBody User user, HttpSession session) {
		User foundUser = userService.getUser(user);
		
		if (foundUser != null) {
			if (foundUser.getPassword().equals(user.getPassword())) {
				session.setAttribute("loginUser", foundUser);
				
				return new ResponseDto<String>(HttpStatus.OK.value(), "Login successful");
			}
			else
				return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), "Invalid password");
		}
		else
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), user.getId() + " doesn't exist");
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
}
