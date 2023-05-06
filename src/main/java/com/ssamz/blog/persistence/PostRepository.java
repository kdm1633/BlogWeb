package com.ssamz.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssamz.blog.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
