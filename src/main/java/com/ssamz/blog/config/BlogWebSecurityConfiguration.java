package com.ssamz.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class BlogWebSecurityConfiguration {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/", "/webjars/**", "/css/**", "/js/**", "/image/**").permitAll()
				.requestMatchers("/signup", "/login", "/logout").permitAll()
				.anyRequest().authenticated()
			)
			.csrf((csrf) -> csrf
				.disable()
			)
			.formLogin((formLogin) -> formLogin
				.loginPage("/login")
//				.loginProcessingUrl("/securitylogin")	// default: /login
			)
			.logout((logout) -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
			);
		
		return http.build();
	}
}
