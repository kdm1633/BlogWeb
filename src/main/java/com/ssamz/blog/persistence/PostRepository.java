package com.ssamz.blog.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.ssamz.blog.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	Optional<Post> findByNum(int num);
	void deleteByNum(int num);
}
