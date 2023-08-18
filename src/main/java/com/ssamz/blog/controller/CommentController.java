package com.ssamz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssamz.blog.domain.Comment;
import com.ssamz.blog.domain.User;
import com.ssamz.blog.dto.ResponseDto;
import com.ssamz.blog.persistence.CommentRepository;
import com.ssamz.blog.security.UserDetailsImpl;
import com.ssamz.blog.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postNum}/comment")
	public @ResponseBody ResponseDto<String> submitComment(@PathVariable int postNum, @RequestBody Comment comment, HttpSession session, @AuthenticationPrincipal UserDetailsImpl principal) {
//		User principal = (User)session.getAttribute("principal");
		commentService.submitComment(postNum, comment, principal.getUser());
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "Comment registered.");
	}
	
	@DeleteMapping("/comment/{num}")
	public @ResponseBody ResponseDto<String> deleteComment(@PathVariable int num) {
		commentService.deleteComment(num);
		
		return new ResponseDto<String>(HttpStatus.OK.value(), "Comment deleted.");
	}
}