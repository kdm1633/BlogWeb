package com.ssamz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssamz.blog.domain.User;
import com.ssamz.blog.service.KakaoLoginService;
import com.ssamz.blog.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoLoginController {
	@Autowired
	private KakaoLoginService kakaoLoginService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	@GetMapping("/oauth/kakao")
	public String getKakaoToken(String code, HttpServletRequest request) {
		String accessToken = kakaoLoginService.getAccessToken(code);
		
		User kakaoUser = kakaoLoginService.getUserInfo(accessToken);
		
		User foundUser = userService.getUser(kakaoUser);
		
		if (foundUser == null)
			userService.signup(kakaoUser);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoPassword);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		HttpSession session = request.getSession();
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		return "redirect:/";
	}
}
