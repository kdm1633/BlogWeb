package com.ssamz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.blog.domain.OauthType;
import com.ssamz.blog.domain.RoleType;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.persistence.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Transactional
	public void signup(User user) {
		user.setPassword(pwEncoder.encode(user.getPassword()));
		user.setRole(RoleType.USER);
		if (user.getOauth() == null) user.setOauth(OauthType.BLOG);
		userRepository.save(user);
	}
	
	public boolean hasUsername(User user) {
		if (userRepository.existsByUsername(user.getUsername()))
			return true;
		
		return false;
	}
	
	@Transactional(readOnly = true)
	public User getUser(User user) {
		User foundUser = userRepository.findByUsername(user.getUsername()).orElse(null);
		
		return foundUser;
	}
	
	@Transactional
	public User updateUser(User user) {
		User foundUser = userRepository.findByNum(user.getNum()).get();
		foundUser.setUsername(user.getUsername());
		if (!user.getPassword().isEmpty()) foundUser.setPassword(pwEncoder.encode(user.getPassword()));
		foundUser.setEmail(user.getEmail());
		
		return foundUser;
	}
}