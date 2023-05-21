package com.ssamz.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssamz.blog.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	void deleteByNum(int num);
}