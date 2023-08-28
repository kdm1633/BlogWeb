package com.ssamz.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ssamz.blog.domain.OauthType;
import com.ssamz.blog.domain.RoleType;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.persistence.UserRepository;

@Service
public class OAuth2UserDetailsServiceImpl extends DefaultOAuth2UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${google.default.password}")
	private String googlePassword;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String providerId = oauth2User.getAttribute("sub");
		String email = oauth2User.getAttribute("email");
		String username = email + "_" + providerId;
		String password = passwordEncoder.encode(googlePassword);
		
		User foundUser = userRepository.findByUsername(username).orElse(null);
		
		if (foundUser == null) {
			foundUser = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(RoleType.USER)
					.oauth(OauthType.GOOGLE)
					.build();
			userRepository.save(foundUser);
		}
		
		return new UserDetailsImpl(foundUser, oauth2User.getAttributes());
	}
}
