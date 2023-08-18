package com.ssamz.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BlogWebApplicationTest {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = "abc123@@";
		String encodedPassword = encoder.encode(password);
		
		System.out.println("password before encoding: " + password);
		System.out.println("password after encoding: " + encodedPassword);
		System.out.println("Comparison for the two passwords: " + encoder.matches(password, encodedPassword));
	}
}
