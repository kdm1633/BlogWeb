package com.ssamz.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssamz.blog.service.PostService;

@Controller
public class IndexController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/")
	public String getIndex(Model model, @PageableDefault(size = 3, sort = "num", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList", postService.getPostList(pageable));
		
		return "post/list";
	}
}
