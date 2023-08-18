package com.ssamz.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssamz.blog.domain.User;

@Mapper
public interface UserMapper {
	@Insert("INSERT INTO users(num, username, password, email) VALUES((SELECT NVL(MAX(num)+1, 1) FROM users), #{username}, #{password}, #{email})")
	public void insertUser(User user);
	
	@Update("UPDATE users SET password = #{password}, email = #{email} WHERE username = #{username}")
	public void updateUser(User user);
	
	@Delete("DELETE FROM users WHERE username = #{username}")
	public void deleteUser(User user);
	
	@Select("SELECT * FROM users WHERE username = #{username}")
	public User getUser(User user);
	
	@Select("SELECT * FROM users ORDER BY username DESC")
	public List<User> getUserList();
}
