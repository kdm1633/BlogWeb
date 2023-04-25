package com.ssamz.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssamz.blog.domain.User;
import com.ssamz.blog.persistence.UserDao;

@SpringBootTest
class UserDaoTest {
	@Autowired
	private UserDao userDao;
	
	@Test
	void getUserListTest() {
		User user = User.builder()
				.id("test")
				.password("test123")
				.email("test@gmail.com")
				.build();
		
		int beforeSize = userDao.getUserList().size();
		userDao.insertUser(user);
		int afterSize = userDao.getUserList().size();
		
		assertEquals(beforeSize + 1, afterSize);
	}
}
