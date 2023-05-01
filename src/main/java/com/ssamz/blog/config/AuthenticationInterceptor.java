package com.ssamz.blog.config;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ssamz.blog.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		if (loginUser == null)
			response.sendRedirect("/login");
		
		return true;
	}
}
