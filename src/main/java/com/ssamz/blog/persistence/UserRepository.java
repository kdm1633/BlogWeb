package com.ssamz.blog.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssamz.blog.domain.User;

@Repository	// It is possible to omit @Repository.
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByNum(int num);
	boolean existsById(String id);
	User findById(String id);
}
