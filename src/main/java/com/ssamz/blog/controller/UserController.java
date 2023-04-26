package com.ssamz.blog.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.RoleType;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.exception.BlogException;
import com.ssamz.blog.persistence.UserRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{num}")
	public @ResponseBody User getUser(@PathVariable int num) {
		User user = userRepository.findById(num).orElseThrow(() -> {
			return new BlogException(num + "번 회원이 없습니다.");
		});
		
		return user;
	}
	
	@GetMapping("/list")
	public @ResponseBody List<User> getUserList() {
		return userRepository.findAll();
	}
	
	// page method 1
	/* @GetMapping("/list/{page}")
	public @ResponseBody Page<User> getUserListPage(@PathVariable int page) {
		Pageable pageable = PageRequest.of(page, 2, Sort.Direction.DESC, "num", "id");
		
		return userRepository.findAll(pageable);
	} */
	
	// page method 2
	@GetMapping("/list/page")
	public @ResponseBody Page<User> getUserListPage(
			@PageableDefault(page = 0, size = 2, direction = Direction.DESC, sort = {"num", "id"}) Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	@PostMapping("/signup")
	public @ResponseBody String signup(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return user.getId() + " 회원가입 성공";
	}
	
	@Transactional
	@PutMapping("/update")
	public @ResponseBody String modifyUser(@RequestBody User user) {
		User selectedUser = userRepository.findById(user.getNum()).orElseThrow(() -> {
			return new BlogException(user.getNum() + "번 회원이 없습니다.");
		});
		
		selectedUser.setId(user.getId());
		selectedUser.setPassword(user.getPassword());
		selectedUser.setEmail(user.getEmail());
		
//		userRepository.save(selectedUser);
		
		return "회원수정 성공";
	}
	
	@DeleteMapping("/delete/{num}")
	public @ResponseBody String deleteUser(@PathVariable int num) {
		userRepository.deleteById(num);
		
		return "회원삭제 성공";
	}
}
