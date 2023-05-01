package com.ssamz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.blog.domain.RoleType;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.persistence.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public boolean existentId(User user) {
		if (userRepository.existsById(user.getId()))
			return true;
		
		return false;
	}
	
	public User getUser(User user) {
		User foundUser = userRepository.findById(user.getId());
		
		return foundUser;
	}
	
	@Transactional
	public void signup(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}