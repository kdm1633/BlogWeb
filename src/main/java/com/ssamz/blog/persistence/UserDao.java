package com.ssamz.blog.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssamz.blog.domain.User;

@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insertUser(User user) {
		sqlSessionTemplate.insert("insertUser", user);
	}
	
	public void updateUser(User user) {
		sqlSessionTemplate.update("updateUser", user);
	}
	
	public void deleteUser(User user) {
		sqlSessionTemplate.delete("deleteUser", user);
	}
	
	public User getUser(User user) {
		return sqlSessionTemplate.selectOne("getUser", user);
	}
	
	public List<User> getUserList() {
		return sqlSessionTemplate.selectList("getUserList");
	}
}
