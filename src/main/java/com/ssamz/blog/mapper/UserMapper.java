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
	@Insert("INSERT INTO users(num, id, password, email) VALUES((SELECT NVL(MAX(num)+1, 1) FROM users), #{id}, #{password}, #{email})")
	public void insertUser(User user);
	
	@Update("UPDATE users SET password = #{password}, email = #{email} WHERE id = #{id}")
	public void updateUser(User user);
	
	@Delete("DELETE FROM users WHERE id = #{id}")
	public void deleteUser(User user);
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	public User getUser(User user);
	
	@Select("SELECT * FROM users ORDER BY id DESC")
	public List<User> getUserList();
}
