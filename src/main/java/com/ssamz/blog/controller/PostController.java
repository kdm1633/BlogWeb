package com.ssamz.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.Post;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.security.UserDetailsImpl;
import com.ssamz.blog.service.PostService;
import com.ssamz.dto.PostDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/list")
	public String getPostList(Model model, @PageableDefault(size = 3, sort = "num", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList", postService.getPostList(pageable));
		
		return "post/list";
	}
	
	@GetMapping("/postup")
	public String postup() {
		return "post/postup";
	}
	
	@PostMapping("/postup")
	public @ResponseBody ResponseDto<String> postup(@Valid @RequestBody PostDto postDto, BindingResult bindingResult, HttpSession session, @AuthenticationPrincipal UserDetailsImpl principal) {
		Post post = modelMapper.map(postDto, Post.class);
		
//		User principal = (User)session.getAttribute("principal");
		post.setUser(principal.getUser());
		postService.postup(post);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "New post registered.");
	}
	
	@GetMapping("/{num}")
	public String getPost(@PathVariable int num, Model model) {
		model.addAttribute("post", postService.getPost(num));
		
		return "post/detail";
	}
	
	
	@GetMapping("/edit/{num}")
	public String editPost(@PathVariable int num, Model model) {
		model.addAttribute("post", postService.getPost(num));
		
		return "post/edit";
	}
	
	@PutMapping("/edit")
	public @ResponseBody ResponseDto<String> editPost(@RequestBody Post post) {
		postService.editPost(post);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "Post edited.");
	}
	
	@DeleteMapping("/{num}")
	public @ResponseBody ResponseDto<String> deletePost(@PathVariable int num) {
		postService.deletePost(num);
		
		return  new ResponseDto<String>(HttpStatus.OK.value(), "Post deleted.");
	}
}
