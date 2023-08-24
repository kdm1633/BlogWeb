package com.ssamz.blog.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssamz.blog.domain.OauthType;
import com.ssamz.blog.domain.RoleType;
import com.ssamz.blog.domain.User;

@Service
public class KakaoLoginService {
	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	public String getAccessToken(String code) {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "6d081046584937b4c5ebbb5add56be18");
		body.add("redirect_uri", "http://localhost:8080/oauth/kakao");
		body.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(
			"https://kauth.kakao.com/oauth/token", 
			HttpMethod.POST, 
			requestEntity, 
			String.class
		);
		
		String jsonStr = responseEntity.getBody();
		
		Gson gson = new Gson();
		Map<String, Object> responseMap = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>(){}.getType());
		
		return (String)responseMap.get("access_token");
	}
	
	public User getUserInfo(String accessToken) {
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + accessToken);
		header.add("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			requestEntity,
			String.class
		);
		
		String userStr = responseEntity.getBody();
		
		Gson gson = new Gson();
		Map<String, Object> responseMap = gson.fromJson(userStr, new TypeToken<Map<String, Object>>(){}.getType());
		
		double id = (double)responseMap.get("id");
		String nickname = ((Map<String, String>)responseMap.get("properties")).get("nickname");
		String email = ((Map<String, String>)responseMap.get("kakao_account")).get("email");
		
		User user = new User();
		user.setUsername(email);
		user.setPassword(kakaoPassword);
		user.setEmail(email);
		user.setRole(RoleType.USER);
		user.setOauth(OauthType.KAKAO);
		
		return user;
	}
}
