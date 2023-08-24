package com.ssamz.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ssamz.blog.security.UserDetailsServiceImpl;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class BlogWebSecurityConfiguration {
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl);
		
		return authenticationManagerBuilder.build();
	}
	
//	@Bean
//	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/", "/webjars/**", "/css/**", "/js/**", "/image/**", "/oauth/**").permitAll()
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
