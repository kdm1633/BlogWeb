package com.ssamz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.Post;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/postup")
	public String postup() {
		return "post/postup";
	}
	
	@PostMapping("/postup")
	public @ResponseBody ResponseDto<String> postup(@RequestBody Post post, HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		post.setUser(loginUser);
		postService.postup(post);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "New post registred.");
	}
	
	@GetMapping("/{id}")
	public String getPost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		
		return "post/detail";
	}
	
	@GetMapping("/list")
	public String getPostList(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList", postService.getPostList(pageable));
		
		return "post/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editPost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		
		return "post/edit";
	}
	
	@PutMapping("/edit")
	public @ResponseBody ResponseDto<String> editPost(@RequestBody Post post) {
		postService.editPost(post);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "Post edited.");
	}
	
	@DeleteMapping("/delete/{id}")
	public @ResponseBody ResponseDto<String> deletePost(@PathVariable int id) {
		postService.deletePost(id);
		
		return  new ResponseDto<String>(HttpStatus.OK.value(), "Post deleted.");
	}
}
