package com.ssamz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.blog.domain.Comment;
import com.ssamz.blog.domain.Post;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.persistence.CommentRepository;
import com.ssamz.blog.persistence.PostRepository;

@Service
public class CommentService {
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Transactional
	public void submitComment(int postNum, Comment comment, User loginUser) {
		Post post = postRepository.findByNum(postNum).get();
		comment.setPost(post);
		comment.setUser(loginUser);
		commentRepository.save(comment);
	}
	
	@Transactional
	public void deleteComment(int num) {
		commentRepository.deleteByNum(num);
	}
}
