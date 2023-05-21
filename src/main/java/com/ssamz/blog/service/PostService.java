package com.ssamz.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssamz.blog.domain.Post;
import com.ssamz.blog.persistence.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	
	@Transactional
	public void postup(Post post) {
		postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public Post getPost(int num) {
		return postRepository.findByNum(num).get();
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostList() {
		return postRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Post> getPostList(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
	@Transactional
	public void editPost(Post post) {
		Post foundPost = postRepository.findById(post.getNum()).get();
		foundPost.setTitle(post.getTitle());
		foundPost.setContent(post.getContent());
	}
	
	@Transactional
	public void deletePost(int num) {
		postRepository.deleteByNum(num);
	}
}
